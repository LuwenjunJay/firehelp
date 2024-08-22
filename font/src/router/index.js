import VueRouter from 'vue-router'
import Vue from 'vue'

Vue.use(VueRouter)

const routes = [
	{
		path:'/',
		redirect:"/zitai"
	},
	{
		path:'/zitai',
		component: ()=> import('../views/zitai.vue')
	},
	{
		path:'/control',
		component:()=> import('../views/control.vue')
		
	}
]


const router = new VueRouter({
	mode:'history',
	routes
	
	
	
})


export default router