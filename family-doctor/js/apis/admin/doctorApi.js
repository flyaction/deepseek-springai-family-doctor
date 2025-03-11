window.doctorApi = {

    doChat: function(bo) {
        return instance({
            url: '/ollama/ai/v3/doctor/stream',
            method: 'post',
            data: bo
        })
    },

    getRecords: function(who) {
        return instance({
            url: '/ollama/getRecords?who=' + who,
            method: 'get',
        })
    },

}



