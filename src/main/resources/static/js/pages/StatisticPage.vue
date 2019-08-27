<template>
    <v-container>
        <!-- Stack the columns on mobile by making one full-width and the other half-width -->

        <v-row>
            <v-col
                    cols="6"
                    md="4"
            >
                <v-card
                        class="pa-2"
                        outlined

                >



                    <v-chip-group
                            column
                            active-class="primary--text"
                            multiple
                            v-model="yearSelector"
                    >
                        <v-chip>
                            Год рождения
                        </v-chip>
                        <v-chip>
                            Календарный год
                        </v-chip>
                    </v-chip-group>
                </v-card>
            </v-col>

            <v-col
                    cols="6"
                    md="4"
            >
                <v-card
                        class="pa-2"
                        outlined
                        tile
                >
                    <v-chip-group
                            column
                            active-class="primary--text"
                            v-model="birthYearSelector"
                    >
                        <v-chip>
                            За определенный период
                        </v-chip>
                        <v-chip>
                            За определенные года
                        </v-chip>
                    </v-chip-group>


                    <v-combobox v-if="birthManualSelectionMode"
                                v-model="birthYearData"
                                :items="dates"
                                label="Выберите года"
                                multiple
                                chips
                    ></v-combobox>

                    <template v-if="birthRangeSelectionMode">
                        <v-combobox
                                v-model="birthYearFrom"
                                :items="dates"
                                chips
                                label="От"
                        ></v-combobox>
                            -
                        <v-combobox
                                v-model="birthYearTo"
                                :items="dates"
                                chips
                                label="До"
                        ></v-combobox>
                    </template>

                </v-card>
            </v-col>

            <v-col
                    cols="6"
                    md="4"
            >
                <v-card
                        class="pa-2"
                        outlined
                        tile
                >
                    .col-6 .col-md-4
                </v-card>
            </v-col>
        </v-row>



        <v-row>
            <v-col
                    cols="12"
                    md="8"
            >
                <v-card
                        class="pa-2"
                        outlined
                        tile
                >
                    .col-12 .col-md-8
                </v-card>
            </v-col>
            <v-col
                    cols="6"
                    md="4"
            >
                <v-card
                        class="pa-2"
                        outlined
                        tile
                >
                    .col-6 .col-md-4
                </v-card>
            </v-col>
        </v-row>

        <!-- Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
        <v-row>
            <v-col
                    v-for="n in 3"
                    :key="n"
                    cols="6"
                    md="4"
            >
                <v-card
                        class="pa-2"
                        outlined
                        tile
                >
                    .col-6 .col-md-4
                </v-card>
            </v-col>
        </v-row>

        <!-- Columns are always 50% wide, on mobile and desktop -->
        <v-row>
            <v-col
                    v-for="n in 2"
                    :key="n"
                    cols="6"
            >
                <v-card
                        class="pa-2"
                        outlined
                        tile
                >
                    .col-6
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>

    import deathTableAPI from 'api/deathTable.js'
    import LineChart from 'components/LineChart.js'

    export default {
        name: "StatisticPage",
        components: {
            LineChart
        },
        data() {
            return {
                yearSelector: null,
                birthYearSelector: null,

                birthRangeSelectionMode: false,
                birthYearFrom: null,
                birthYearTo: null,

                ageForBirthRangeSelectionMode: false,
                ageForBirthManualSelectionMode: false,

                birthManualSelectionMode: false,
                birthYearData: [],

                dates: null,



                datacollection: null,
                loaded: false,
                chartOptions: {
                    responsive: true,
                    title: {
                        display: true,
                        text: 'Chart.js Line Chart'
                    },
                    tooltips: {
                        mode: 'index',
                        intersect: false,
                    },
                    hover: {
                        mode: 'nearest',
                        intersect: true
                    },
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'Month'
                            }
                        }],
                        yAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'Value'
                            }
                        }]
                    }
                }
            }
        },
        created(){

            this.fillDates()
        },
        computed: {
            datesComputed() {
                return this.dates
            },
        },
        methods: {


            async fillDates(){

               const result = await deathTableAPI.getAllBirthYears();

               this.dates = result.data
            },

            async test(){
                try {
                    const result = await deathTableAPI.getDeathTable({year: 2000,
                        ageFrom: 18, ageTo: 27})

                    const data = result.data

                    console.log(data)

                    /*let maleData = {
                        pointBackgroundColor: "#FFFFFF",
                        pointBorderColor: "#DDDDDD",
                        backgroundColor: '#FFA500',
                        label: 'Мужчины',
                        data: []
                    }
                    let femaleData = {
                        pointBackgroundColor: "#FFFFFF",
                        pointBorderColor: "#DDDDDD",
                        backgroundColor: '#49FF00',
                        label: 'Женщины',
                        data: []
                    }

                    /!*data.deathNotesMale.forEach(function(item, i, arr) {
                        labels.push(item.x)
                        maleData.data.push(item.l)
                    })*!/
                    let labels = []

                    if(data.deathNotesMale.length === data.deathNotesFemale.length){

                        for (let i = data.deathNotesMale.length - 1; i >= 0; i--) {
                            labels.push(data.deathNotesMale[i].x)
                            maleData.data.push(data.deathNotesMale[i].l)
                            femaleData.data.push(data.deathNotesFemale[i].l)
                        }


                    } else {
                        for (let i = data.deathNotesMale.length - 1; i >= 0; i--) {
                            labels.push(data.deathNotesMale[i].x)
                            maleData.data.push(data.deathNotesMale[i].l)
                        }
                        for (let i = data.deathNotesFemale.length - 1; i >= 0; i--) {
                            labels.push(data.deathNotesFemale[i].x)
                            femaleData.data.push(data.deathNotesFemale[i].l)
                        }

                    }

                    this.datacollection = {
                        labels,
                        datasets: [
                            maleData,
                            femaleData
                        ]
                    }

                    this.loaded = true*/

                } catch (e) {
                    console.log(JSON.stringify(e))
                }

            },

        },
        watch: {
            yearSelector:  function () {
                console.log(this.yearSelector)
            },
            birthYearSelector: function(){

                if(this.birthYearSelector){
                    if(this.birthYearSelector === 0 ) {
                        this.birthRangeSelectionMode = true
                        this.birthManualSelectionMode = false
                    } else {
                        this.birthRangeSelectionMode = false
                        this.birthManualSelectionMode = true
                    }
                }
                console.log(this.birthRangeSelectionMode)
                console.log(this.birthManualSelectionMode)
            }


        }
    }
</script>

<style scoped>
    .statistic-container{
        background-color: #262626;
        border-color: #262626;
    }
</style>