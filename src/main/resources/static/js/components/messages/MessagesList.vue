<template>
    <v-container>
        <message-form :messages="messages" :messageAttr="message"/>

        <v-list shaped>
            <v-subheader>MESSAGES</v-subheader>
            <v-list-item-group color="primary">
                <!--<v-list-item v-for="(item, i) in items" :key="i">-->

                <!--</v-list-item>-->
                <message-row v-for="message in messages" :key="message.id" :message="message" :messages="messages"
                             :editMessage="editMessage" :deleteMessage="deleteMessage"/>
            </v-list-item-group>
        </v-list>
    </v-container>
    <!--<div>-->
    <!--<message-form :messages="messages" :messageAttr="message"/>-->
    <!--<message-row v-for="message in messages" :key="message.id" :message="message" :messages="messages"-->
    <!--:editMessage="editMessage" :deleteMessage="deleteMessage"/>-->
    <!--</div>-->


</template>

<script>
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'

    export default {
        name: "MessagesList",
        props: ['messages'],
        components: {
            MessageRow, MessageForm
        },


        data() {
            return {
                message: null
            }
        },
        methods: {
            editMessage(message) {
                this.message = message
            },
            deleteMessage(message) {
                this.$resource('/messages{/id}').remove({id: message.id}).then(result => {
                    if (result.ok) {
                        this.messages.splice(this.messages.indexOf(this.message), 1)
                    }
                })
            }
        }
    }

    // data: () => ({
    //     selectedItem: null,
    //     items: [
    //         {text: 'Real-Time', icon: 'mdi-clock'},
    //         {text: 'Audience', icon: 'mdi-account'},
    //         {text: 'Conversions', icon: 'mdi-flag'},
    //     ],
    // }),

</script>

<style scoped>

</style>