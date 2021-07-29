<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">

    <v-app>
        <v-app-bar app>
            <v-app-bar-nav-icon @click="drawer = true" v-if="profile">
            </v-app-bar-nav-icon>

            <v-toolbar-title>Sarafan</v-toolbar-title>
            <v-btn text @click="showMessages" v-if="profile" :disabled="$route.path==='/'">
                Messages
            </v-btn>
            <v-spacer></v-spacer>

            <span v-if="profile">
                <v-list-item>
                    <v-list-item-avatar>
                        <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <v-img :src="profile.userpic" @click="showProfile"
                                       :disabled="$route.path==='/profile'"
                                       v-bind="attrs" v-on="on"/>
                            </template>
                            <span>Press for open profile</span>
                        </v-tooltip>
                    </v-list-item-avatar>

                    <v-list-item-content>
                        <v-btn href="/logout" icon>
                    <v-icon>mdi-logout</v-icon>
                </v-btn>
                    </v-list-item-content>
                </v-list-item>
            </span>

        </v-app-bar>
        <!---->
        <v-navigation-drawer v-model="drawer" absolute temporary>
            <v-list nav dense>
                <v-list-item-group v-model="group" active-class="deep-purple--text text--accent-4">
                    <v-list-item @click="showMessages">
                        <v-list-item-icon>
                            <v-icon>mdi-home</v-icon>
                        </v-list-item-icon>
                        <v-list-item-title>Home</v-list-item-title>
                    </v-list-item>
                    <v-list-item @click="showProfile">
                        <v-list-item-icon>
                            <v-icon>mdi-home</v-icon>
                        </v-list-item-icon>
                        <v-list-item-title>Profile</v-list-item-title>
                    </v-list-item>
                </v-list-item-group>
            </v-list>
        </v-navigation-drawer>
        <!---->

        <v-main>
            <v-container>
                <router-view></router-view>
            </v-container>
        </v-main>
    </v-app>

</template>

<script>


    import {mapState, mapMutations} from 'vuex'
    import {addHandler} from "util/ws"

    export default {
        name: "App",
        data: () => ({
            drawer: false,
            group: null,
        }),
        computed: mapState(['profile']),
        methods: {
            ...mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
            showMessages() {
                this.$router.push("/")
            },
            showProfile() {
                this.$router.push("/profile")
            }
        },
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {

                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break;
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
                            break;
                        default:
                            console.error('Looks like the event type if unknown - ${data.eventType}')
                    }
                } else {
                    console.error('Looks like the object type if unknown - ${data.objectType}')
                }
            })
        },
        beforeMount() {
            if (!this.profile) {
                this.$router.replace("/auth")
            }
        }
    }
</script>

<style scoped>

</style>