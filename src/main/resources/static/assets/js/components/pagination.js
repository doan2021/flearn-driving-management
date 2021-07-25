Vue.component('pagination', {
    props: {
        totalPages: {
            type: Number,
            required: false,
            default: 0
        },
        total: {
            type: Number,
            required: false,
            default: 0
        },
        currentPage: {
            type: Number,
            required: false,
            default: 0
        }
    },
    computed: {
        isInFirstPage() {
          return this.currentPage === 0;
        },
        isInLastPage() {
          return this.currentPage === this.totalPages - 1;
        },
    },
    methods: {
        onClickFirstPage() {
          this.$emit('pagechanged', 0);
        },
        onClickPreviousPage() {
          this.$emit('pagechanged', this.currentPage - 1);
        },
        onClickPage(page) {
          this.$emit('pagechanged', page);
        },
        onClickNextPage() {
          this.$emit('pagechanged', this.currentPage + 1);
        },
        onClickLastPage() {
          this.$emit('pagechanged', this.totalPages - 1);
        }
    },
    template:'<nav v-if="totalPages > 1">'
        +'        <ul class="pagination m-0">'
        +'                <li class="page-item" v-if="currentPage > 0">'
        +'                    <button type="button" class="page-link" @click="onClickPreviousPage"'
        +'                        <i class="fas fa-angle-double-left"></i>'
        +'                    </button>'
        +'                </li>'
        +'                <li class="page-item" v-for="i in totalPages" :key="i" :class="{ active : currentPage == (i - 1) }">'
        +'                    <button class="page-link" @click="onClickPage(i-1)">{{ i }}</button>'
        +'                </li>'
        +'                <li class="page-item" v-if="currentPage < (totalPages - 1)">'
        +'                    <button type="button" class="page-link" @click="onClickNextPage">'
        +'                        <i class="fas fa-angle-double-right"></i>'
        +'                    </button>'
        +'                </li>'
        +'            </ul>'
        +'        </nav>'
})