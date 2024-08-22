<template>
  <div id="app">

   
   <div style="width:100%;display:flex;">
   <leftbar></leftbar>
   <router-view></router-view>
   </div>
  </div>
</template>

<script>

import leftbar from '../src/components/leftbar.vue'
export default {
  name: 'App',
  components:{
	  leftbar
	  
  }
 
}
</script>

<style>
.el-button {
  touch-action: manipulation; /* 阻止双击放大 */
  -webkit-tap-highlight-color: transparent; /* 禁用点击高亮效果 */
}
</style>
