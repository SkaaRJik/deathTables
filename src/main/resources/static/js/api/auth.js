import Vue from 'vue'
import Store from 'vue-modules/vuex-store'



export default {
    signIn: signInDetails => {return Vue.resource('/api/public/auth/signin').save({}, signInDetails)},
    signUp: signUpDetails => {return Vue.resource('/api/public/auth/signup').save({}, signUpDetails)},
    isEmailRegistrated: email => {return Vue.resource('/api/public/auth/check-email').get({email: email})},
    updateTokens(tokens) {return Vue.resource('/api/private/auth/refresh-token').update({}, tokens)},
    runTokenWatcher() {
        const savedThis = this
        if(Store.state.profile!=null) {
            let timerId = setTimeout(function tick() {
                console.log('Old token : \n' + JSON.stringify(Store.state.profile.token))
                savedThis.updateTokens(Store.state.profile.token).then(result => {
                    result.json().then(newTokens => {
                        Store.dispatch("updateTokens", newTokens)
                        timerId = setTimeout(tick, newTokens.accessTokenExpiredIn)
                    })
                })


                Store.dispatch('setTokenRefresher', timerId)
            }, Store.state.profile.token.accessTokenExpiredIn);
        }
        /*if(Store.state.profile!=null){
            console.log('Old token : \n' + Store.state.profile.token)
            this.updateTokens(Store.state.profile.token).then(result => {
                result.json().then(newTokens => {
                    console.log('New token : \n' + newTokens)
                    Store.dispatch('updateTokens', newTokens);

                    const tokenRefresher =  setTimeout(function () {
                        console.log("runTokenWatcher starting by timer")
                        this.runTokenWatcher()
                    }, newTokens.accessTokenExpiredIn)

                    Store.dispatch('setTokenRefresher', tokenRefresher)
                    console.log("tokenRefresher setted. \nFINISH!")
                })
            })
        }*/
    }

}