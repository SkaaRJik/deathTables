import Vue from 'vue'
import Store from 'vue-modules/vuex-store'

import axios from 'axios'

export default {

    getTableByBirthYear: options => {return Vue.resource('/api/public/death_table').get({options: options},{params: {}})},
    getTableByBirthYearThroughAxios: options => {return axios.get('/api/public/death_table', {
        params: {
            options: options
        }
    })},


    }
