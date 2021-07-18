<template>
    <!--<div>-->
    <!--<div>{{message.text}}-->
    <!--<span>-->
    <!--<input type="button" value="Edit" @click="edit">-->
    <!--<input type="button" value="Del" @click="del">-->
    <!--</span>-->
    <!--</div>-->
    <!--</div>-->

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
    export default {
        name: "MessageRow",
        props: ['message', 'messages', 'editMessage', 'deleteMessage'],
        methods: {
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.deleteMessage(this.message)
            }
        }
    }
</script>

<style scoped>

</style>