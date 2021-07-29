<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">


    <v-dialog min-width="400px" max-width="400px" v-model="dialog" persistent :xs6="!$vuetify.breakpoint.xsOnly">
        <div>
            <v-tabs centered icons-and-text>
                <v-tabs-slider></v-tabs-slider>
                <v-tab v-for="i in tabs" :key="i.name">
                    <v-icon large>{{ i.icon }}</v-icon>
                    <div class="caption py-1">{{ i.name }}</div>
                </v-tab>
                <v-tab-item>
                    <v-card>
                        <v-card-text>
                            <v-form ref="loginForm" v-model="valid" lazy-validation>
                                <v-row align="center" justify="center">
                                    <v-col cols="8">
                                        <v-text-field v-model="loginEmail" :rules="loginEmailRules" label="E-mail"
                                                      required></v-text-field>
                                    </v-col>
                                </v-row>
                                <v-row align="center" justify="center">
                                    <v-col cols="8">
                                        <v-text-field v-model="loginPassword" :append-icon="show1?'eye':'eye-off'"
                                                      :rules="[rules.required, rules.min]"
                                                      :type="show1 ? 'text' : 'password'" name="input-10-1"
                                                      label="Password" hint="At least 8 characters" counter
                                                      @click:append="show1 = !show1"></v-text-field>
                                    </v-col>
                                </v-row>
                                <v-row align="center" justify="center">
                                    <v-col class="d-flex" cols="8">
                                        <div>
                                            Authorization by
                                            <v-tooltip bottom>
                                                <template v-slot:activator="{ on, attrs }">
                                                    <v-btn href="/login" icon v-bind="attrs"
                                                           v-on="on" @click="dialog=false">
                                                        <v-icon color="#F44336">mdi-google</v-icon>
                                                    </v-btn>
                                                </template>
                                                <span>Authorization by Google</span>
                                            </v-tooltip>
                                        </div>
                                    </v-col>
                                </v-row>
                                <v-row align="center" justify="center">
                                    <v-col cols="8">
                                        <v-btn x-large block :disabled="!valid" @click="validate">
                                            Login
                                        </v-btn>
                                    </v-col>
                                </v-row>
                            </v-form>
                        </v-card-text>
                    </v-card>
                </v-tab-item>
                <v-tab-item>
                    <v-card>
                        <v-card-text>
                            <v-form ref="registerForm" v-model="valid" lazy-validation>
                                <v-layout row>
                                    <v-row justify="center">
                                        <v-col cols="5">
                                            <v-text-field v-model="firstName" :rules="[rules.required]"
                                                          label="First Name"
                                                          maxlength="20" required></v-text-field>
                                        </v-col>
                                        <v-col cols="5">
                                            <v-text-field v-model="lastName" :rules="[rules.required]" label="Last Name"
                                                          maxlength="20" required></v-text-field>
                                        </v-col>
                                    </v-row>
                                </v-layout>
                                <v-layout column>
                                    <v-row justify="center">
                                        <v-col cols="8">
                                            <v-text-field v-model="email" :rules="emailRules" label="E-mail"
                                                          required></v-text-field>
                                        </v-col>
                                    </v-row>
                                    <v-row justify="center">
                                        <v-col cols="8">
                                            <v-text-field v-model="password"
                                                          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                                                          :rules="[rules.required, rules.min]"
                                                          :type="show1 ? 'text' : 'password'" name="input-10-1"
                                                          label="Password" hint="At least 8 characters" counter
                                                          @click:append="show1 = !show1"></v-text-field>
                                        </v-col>
                                    </v-row>
                                    <v-row align="center" justify="center">
                                        <v-col cols="8">
                                            <v-text-field block v-model="verify"
                                                          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                                                          :rules="[rules.required, passwordMatch]"
                                                          :type="show1 ? 'text' : 'password'" name="input-10-1"
                                                          label="Confirm Password" counter
                                                          @click:append="show1 = !show1"></v-text-field>
                                        </v-col>
                                    </v-row>
                                    <v-row align="center" justify="center">
                                        <v-col cols="8">
                                            <v-btn block :disabled="!valid" @click="validate">
                                                Register
                                            </v-btn>
                                        </v-col>
                                    </v-row>
                                </v-layout>
                            </v-form>
                        </v-card-text>
                    </v-card>
                </v-tab-item>
            </v-tabs>
        </div>
    </v-dialog>


</template>

<script>
    export default {
        name: "Auth",
        data: () => ({
            dialog: true,
            tabs: [
                {name: "Login", icon: "mdi-account"},
                {name: "Register", icon: "mdi-account-outline"}
            ],
            tab: 0,

            valid: true,
            login: '',
            loginRules: [
                v => !!v || 'Name is required',
                v => (v && v.length <= 10) || 'Name must be less than 10 characters',
            ],
            email: '',
            emailRules: [
                v => !!v || 'E-mail is required',
                v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
            ],

            firstName: "",
            lastName: "",
            password: "",

            verify: "",
            loginPassword: "",
            loginEmail: "",
            loginEmailRules: [
                v => !!v || "Required",
                v => /.+@.+\..+/.test(v) || "E-mail must be valid"
            ],
            emailRules: [
                v => !!v || "Required",
                v => /.+@.+\..+/.test(v) || "E-mail must be valid"
            ],

            show1: false,
            rules: {
                required: value => !!value || "Required.",
                min: v => (v && v.length >= 8) || "Min 8 characters"
            }
        }),
        computed: {
            passwordMatch() {
                return () => this.password === this.verify || "Password must match";//sldkfjaslkdjflakjflkajsfkdsljfk check for need....
            }
        },
        methods: {
            validate() {
                if (this.$refs.loginForm.validate()) {
                    console.log("print from login form......")
                } else if (this.$refs.registerForm.validate()) {
                    console.log("print from registration form......")
                }else {
                    console.log("print from else block ......")
                }
            },
            reset() {
                this.$refs.form.reset();
            },
            resetValidation() {
                this.$refs.form.resetValidation();
            }
        },
    }
</script>

<style>

</style>


<!--new Vue({-->
<!--el: '#app',-->
<!--vuetify: new Vuetify(),-->
<!--computed: {-->
<!--passwordMatch() {-->
<!--return () => this.password === this.verify || "Password must match";-->
<!--}-->
<!--},-->
<!--methods: {-->
<!--validate() {-->
<!--if (this.$refs.loginForm.validate()) {-->
<!--// submit form to server/API here...-->
<!--}-->
<!--},-->
<!--reset() {-->
<!--this.$refs.form.reset();-->
<!--},-->
<!--resetValidation() {-->
<!--this.$refs.form.resetValidation();-->
<!--}-->
<!--},-->
<!--data: () => ({-->
<!--dialog: true,-->
<!--tab: 0,-->
<!--tabs: [-->
<!--{name:"Login", icon:"mdi-account"},-->
<!--{name:"Register", icon:"mdi-account-outline"}-->
<!--],-->
<!--valid: true,-->

<!--firstName: "",-->
<!--lastName: "",-->
<!--email: "",-->
<!--password: "",-->
<!--verify: "",-->
<!--loginPassword: "",-->
<!--loginEmail: "",-->
<!--loginEmailRules: [-->
<!--v => !!v || "Required",-->
<!--v => /.+@.+\..+/.test(v) || "E-mail must be valid"-->
<!--],-->
<!--emailRules: [-->
<!--v => !!v || "Required",-->
<!--v => /.+@.+\..+/.test(v) || "E-mail must be valid"-->
<!--],-->

<!--show1: false,-->
<!--rules: {-->
<!--required: value => !!value || "Required.",-->
<!--min: v => (v && v.length >= 8) || "Min 8 characters"-->
<!--}-->
<!--})-->
<!--});-->
