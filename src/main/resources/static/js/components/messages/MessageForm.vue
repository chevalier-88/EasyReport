<template>
    <v-layout>
        <v-text-field label="message:" placeholder="input text message" v-model="text" filled></v-text-field>
        <v-btn rounded color="primary" dark @click="save">Save</v-btn>
    </v-layout>

</template>

<script>

    import {mapActions} from 'vuex'

    export default {
        name: "MessageForm",
        props: ['messageAttr'],
        data() {
            return {
                text: ''
            }
        },
        watch: {
            messageAttr: function (newVal, oldVal) {
                this.text = newVal.text;
                this.id = newVal.id;
            }
        },
        methods: {
            ...mapActions(['addMessageAction', 'updateMessageAction']),
            save() {

                const message = {id: this.id, text: this.text}

                if (this.id) {
                    this.updateMessageAction(message)
                } else {
                    this.addMessageAction(message)
                }

                this.text = ''
                this.id = ''
            }
        }
    }
</script>

<style scoped>

</style>