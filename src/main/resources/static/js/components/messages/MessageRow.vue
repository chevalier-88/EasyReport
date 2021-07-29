<template>
    <v-list-item>
        <v-list-item-content>
            {{message.text}}
            <span>
        <input type="button" value="Edit" @click="edit">
        <input type="button" value="Del" @click="del">
            </span>
        </v-list-item-content>
    </v-list-item>
</template>


<script>
    import {mapActions} from 'vuex'

    export default {
        props: ['message', 'editMessage'],
        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.removeMessageAction(this.message)
            }
        }
    }
</script>

<style scoped>
    button {
        background-color: red;
    }

    v-btn {
        background-color: red;
    }
</style>