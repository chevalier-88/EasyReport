<template>
    <v-container>
        <message-form :messageAttr="message"/>

        <v-list shaped>
            <v-subheader>MESSAGES</v-subheader>
            <v-list-item-group color="primary">
                <message-row v-for="message in sortedMessages" :key="message.id" :message="message"
                             :editMessage="editMessage"/>
            </v-list-item-group>
        </v-list>
    </v-container>
</template>

<script>
    import {mapGetters} from 'vuex'
    import MessageRow from '../components/messages/MessageRow.vue'
    import MessageForm from '../components/messages/MessageForm.vue'


    export default {
        name: "MessagesList",

        components: {
            MessageRow, MessageForm
        },


        data() {
            return {
                message: null
            }
        },
        computed: mapGetters(['sortedMessages']),
        methods: {
            editMessage(message) {
                this.message = message
            },
        }
    }


</script>

<style scoped>

</style>