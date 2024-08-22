<template>
	<div style="width:20%;height: 100vh;background-color: #805538;">
		
		<el-row class="tac" >
		  <el-col>
		    <el-menu
		      default-active="/zitai"
		      class="el-menu-vertical-demo"
			  @select="handleSelect"
			   background-color="#805538"
			        text-color="#fff"

			        active-text-color="#ffd04b">
		      <el-submenu index="1">
		        <template slot="title">
		          <i class="el-icon-location"></i>
		          <span>火场救援</span>
		        </template>
		        <el-menu-item-group>
		          <el-menu-item index="/zitai">数据</el-menu-item>
		          <el-menu-item index="/control">控制</el-menu-item>
		        </el-menu-item-group>
		   
		      </el-submenu>
		      
		  
		    </el-menu>
		  </el-col>
		</el-row>
	</div>
</template>

<script>
	export default {
		  methods: {
			  handleSelect(key, keyPath){
				  this.$router.push(key)
			  },
		    
		    }
	}
</script>

<style>
</style>