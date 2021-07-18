<template>

    <v-app>
        <v-app-bar app>
            <v-app-bar-nav-icon></v-app-bar-nav-icon>
            <v-toolbar-title>Sarafan</v-toolbar-title>

            <v-spacer></v-spacer>

            <v-btn icon>
                <v-icon>mdi-magnify</v-icon>
            </v-btn>
            <span v-if="profile">
            <v-btn text href="/profile">
                {{profile.name}}&nbsp;
            </v-btn>

                <v-btn href="/logout" icon>
                    <v-icon>
                        mdi-logout
                    </v-icon>
                </v-btn>
                </span>
        </v-app-bar>


        <v-main>
            <v-container>
                <div v-if="!profile">Need authorization by
                    <a href="/login">Google</a>
                </div>
                <div v-else>
                    <messages-list :messages="messages"/>
                </div>
            </v-container>
        </v-main>
    </v-app>

</template>

<script>
    import MessagesList from 'components/messages/MessagesList.vue'
    import {addHandler} from "util/ws"
    import {getIndex} from "util/collections"

    export default {
        name: "App",
        components: {
            MessagesList
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile,
            }
        },
        created() {
            addHandler(data => {
                let index = getIndex(this.messages, data.id)
                if (index > -1) {
                    this.messages.splice(index, 1, data)
                } else {
                    this.messages.push(data)
                }
            })
        }

    }
</script>

<style scoped>

</style>