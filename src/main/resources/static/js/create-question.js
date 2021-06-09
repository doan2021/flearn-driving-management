var pageListData = new Vue({
    el: '#create-question-app',
    data: {
        srcImagePreview   : '',
        contentAddAnswer  : '',
        numberAnswer      : 2,
        images            : []
    },
    mounted() {
    },
    methods: {
        readURL: function(e){
        	this.images = [];
            if (e.target.files) {
                for (i = 0; i < e.target.files.length; i++) {
                    this.images.push(URL.createObjectURL(e.target.files[i]));
                }
            }
        },
        addNewAnswer: function(){
            if (this.numberAnswer < 10) {
                this.numberAnswer += 1;
            }
        },
        deleteAnswer: function(){
            if (this.numberAnswer > 2) {
                this.numberAnswer -= 1;
            }
        }
    }
});