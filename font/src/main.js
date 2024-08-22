import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router/index.js'
Vue.config.productionTip = false


Vue.use(ElementUI);
Vue.prototype.$BaseUrl = "http://www.coderlu.com:8088"
new Vue({
  render: h => h(App),
  router
}).$mount('#app')
