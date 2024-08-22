<template>
	<div class="control_container" style="width: 100%;display: flex;background-color: #e8b65f;">
	
		<div style="width: 50%;display: flex;flex-direction: column;align-items: center;">
			
		
			<img v-if="pic" :src="$BaseUrl+pic" style="width: 75%;height: 50%;margin-top: 30px;"/>
			
		
		  <el-empty v-show="!pic" description="未连接" style="width: 75%;height: 50%;margin-top: 30px;background-color: white;"></el-empty>
			
			<div style="width: 75%;display: flex;margin-top: 30px;">
			<img  v-if="personImg" :src="$BaseUrl + personImg" style="width: 50%;"/>
			<el-empty v-show="!personImg" description="未连接" style="width: 50%;background-color: white;"></el-empty>
			<div style="width: 50%;display: flex;align-items: center;flex-wrap: wrap;padding: 3%;box-sizing: border-box;">
			<div style="width: 100%;">
			<h2 style="text-align: center;">人物状态</h2>
			</div>
			<el-rate
			  v-model="value"
			  disabled
			  :colors="['#99A9BF', 'pink', '#d14141']">
			</el-rate>

			</div>
		
			
			</div>
		     <div>	
		</div>

	</div>
	<div style="width: 50%;display: flex;flex-direction: column;align-items: center;">
		<div style="width: 600px;height:800px;" id="positionContainer">
			
		</div>
		<h2>当前坐标： {{position}}</h2>
	<!-- 	<h1>目前行驶状态</h1>
		<el-button style="background-color: #de9a37;" circle>
			
			<i class="el-icon-video-pause" v-show="nowState == 69 || nowState == 0" style="font-size: 50px;color: white;"></i>
			<i class="el-icon-arrow-up" v-show="nowState == 1" style="font-size: 50px;color: white;"/>
		    <i class="el-icon-arrow-left" v-show="nowState == 3" style="font-size: 50px;color: white;"></i>
			<i class="el-icon-arrow-right" v-show="nowState == 4" style="font-size: 50px;color: white;"></i>
			<i class="el-icon-arrow-down" v-show="nowState == 2" style="font-size: 50px;color: white;"></i>
			<i class="el-icon-d-arrow-left" v-show="nowState == 70" style="font-size: 50px;transform: rotate(90deg);color: white;"/>
		    <i class="el-icon-d-arrow-left" v-show="nowState == 71" style="font-size: 50px;transform: rotate(90deg);color: white;"/>
		   
		</el-button> -->
		
		
		
	     <div style="width: 100%;display: flex;justify-content: space-around;margin-top: 20PX;">
		 <el-button type="info" round @click="toolHelp(72)" style="background-color: peru;">打开陀螺仪辅助</el-button>
		 <el-button type="info" round @click="toolHelp(73)" style="background-color: brown;">关闭陀螺仪辅助</el-button>
		 <el-button type="info" round @click="toolHelp(79)" style="background-color: peru;">打开激光辅助</el-button>
		 <el-button type="info" round @click="toolHelp(80)" style="background-color: brown;">关闭激光辅助</el-button>
		 <!-- <el-button type="info" round @click="recordAng" style="background-color: peru;">记录该角度</el-button> -->
		 	 </div>
			 <div style="margin-top: 30px;display: flex;">
				 <div style="width: 50%;">
					<el-button @click="move(81)" type="danger" icon="el-icon-phone-outline" round>报警</el-button>
					<br/>
					<el-button @click="move(82)" style="margin-top: 15px;" type="warning" round>取消报警</el-button>
				 </div>
			
			<div style="width: 50%;">
		 <div style="width: 100%;display: flex;justify-content: center;">
		 			 <el-button :style="{backgroundColor: nowState == 70 ? '#d99c9c' : '#d14141'}"  @click="move(70)" circle><i class="el-icon-d-arrow-left" style="font-size: 50px;transform: rotate(90deg);color: white;"/></el-button>
		 			<el-button :style="{backgroundColor: nowState == 1 ? '#d99c9c' : '#d14141'}"    @click="move(1)"><i class="el-icon-arrow-up" style="font-size: 50px;color: white;"/></el-button>	
		 				<el-button :style="{backgroundColor: nowState == 71 ? '#d99c9c' : '#d14141'}" @click="move(71)" circle><i class="el-icon-d-arrow-right" style="font-size: 50px;transform: rotate(90deg);color: white;"/></el-button>
		 				
		 				</div>	
		 				<div style="width: 100%;display: flex;justify-content: center;margin-top: 15px;">	
		 				<el-button :style="{backgroundColor: nowState == 3 ? '#d99c9c' : '#d14141'}"   @click="move(3)"><i class="el-icon-arrow-left" style="font-size: 50px;color: white;"/></el-button>
		 				 <el-button :style="{backgroundColor: nowState == 69 ? '#d99c9c' : '#d14141'}" @click="move(69)" circle><i class="el-icon-video-pause" style="font-size: 50px;color: white;"></i></el-button>
		     <el-button :style="{backgroundColor: nowState == 4 ? '#d99c9c' : '#d14141'}"   @click="move(4)"><i class="el-icon-arrow-right" style="font-size: 50px;color: white;"/></el-button>
		 			</div>	
		 			<div style="margin-top: 15px;width: 100%;display: flex;justify-content: center;">	
					<el-button :style="{backgroundColor: nowState == 77 ? '#d99c9c' : '#d14141'}" @touchstart.native = "isMobile?move(77):''" @touchend.native = "isMobile?move(69):''" @mouseup.native="!isMobile?move(69):''" @mousedown.native="!isMobile?move(77):''"  circle><i class="el-icon-refresh-left" style="font-size: 50px;color: white;"/></el-button>
		 			 <el-button :style="{backgroundColor: nowState == 2 ? '#d99c9c' : '#d14141'}"   @click="move(2)"><i class="el-icon-arrow-down" style="font-size: 50px;color: white;"/></el-button>
		 <el-button :style="{backgroundColor: nowState == 78 ? '#d99c9c' : '#d14141'}" @touchstart.native = "isMobile?move(78):''" @touchend.native = "isMobile?move(69):''" @mouseup.native="!isMobile?move(69):''" @mousedown.native="!isMobile?move(78):''" circle><i class="el-icon-refresh-right" style="font-size: 50px;color: white;"/></el-button>
		 
		 </div>
		 </div>
		 </div>
	</div>
	
	</div>
	</template>

<script>
		import * as echarts from 'echarts';
		import 'echarts-gl';
	export default {
		data() {
			return {
				value: null,
				websocket:null,
				nowState:0,
				pic:"",
				personImg:"",
				positionData:[],
				position:[],
				isMobile:false
			}
		},
		methods:{
			// recordAng(){
			//   this.websocket.send(76)
			//   this.$message.success("记录成功！")
			// },
		  initEcharts(x,y,z){
			 var chartDom = document.getElementById('positionContainer');
			 var myChart = echarts.init(chartDom);
			 var option;
			 
			 
			
			 this.positionData.push([x,y,z])
			 option = {
			   tooltip: {},
			   backgroundColor: '#e8b65f',
			   visualMap: {
			     show: false,
			     dimension: 2,
			     min: 0,
			     max: 30,
			     inRange: {
			       color: [
			         '#313695',
			         '#4575b4',
			         '#74add1',
			         '#abd9e9',
			         '#e0f3f8',
			         '#ffffbf',
			         '#fee090',
			         '#fdae61',
			         '#f46d43',
			         '#d73027',
			         '#a50026'
			       ]
			     }
			   },
			   xAxis3D: {
			     type: 'value'
			   },
			   yAxis3D: {
			     type: 'value'
			   },
			   zAxis3D: {
			     type: 'value'
			   },
			   grid3D: {
			     viewControl: {
			       projection: 'orthographic'
			     }
			   },
			   series: [
			     {
			       type: 'line3D',
			       data: this.positionData,
			       lineStyle: {
			         width: 4
			       }
			     }
			   ]
			 };
			 
			 option && myChart.setOption(option);

		  },
		  toolHelp(state){
				   this.websocket.send(state)
				   if(state == 72){
					   this.$message.success("打开陀螺仪辅助！")
				   }else if(state == 73){
					    this.$message.warning("关闭陀螺仪辅助！")
					   
				   }else if(state == 79){
					     this.$message.success("打开激光辅助！")
				   }else if(state == 80){
					     this.$message.warning("关闭激光辅助！")
				   }
			 
		  },
		  move(state){
			  if(state != 81 && state != 82){
			   this.nowState = state
			   }else if(state == 81){
				   this.$message.success("报警指令已下达")
			   }else{
				   this.$message.success("报警已关闭")
			   }
			   this.websocket.send(state)
		  }
		 
		},
		created(){
			console.log(navigator.userAgent)
			// 检测用户代理字符串
			const isMobile = /iPhone|iPad|iPod|Macintosh|Android/i.test(navigator.userAgent);
			
			if (isMobile) {
			  // 执行移动设备上的操作
			  this.isMobile = true
			}

		},
		mounted(){
			
			if('WebSocket' in window){
			  this.websocket = new WebSocket(`ws://www.coderlu.com:8088/ws/1/2`,)
			  console.log("建立连接")
			  this.websocket.onerror = ()=>{
			    console.log("连接失败")
			  }
			  
			  this.websocket.onopen = ()=>{
			   console.log("连接")
			  }
			  
			  this.websocket.onmessage = (event)=>{
				  
				 if(event.data == 400){
					 
					 this.$message.error("设备不在线！")
				 } else{
					 
				
				  var data = event.data.split(",")
				  if(data.length == 6){
					  
					  
					  this.initEcharts(data[0],data[1],data[2])
					  this.$set(this.position,0,data[0])
					  this.$set(this.position,1,data[1])
					  this.$set(this.position,2,data[2])
					  
				 }
				 else if(data.length == 3){
					  this.pic = data[0]
					  this.personImg = data[1]
					  this.value = parseInt(data[2])
				  }else if(data.length == 1){
				
				   this.pic = data[0]
			
					  
				  }
				
				}
							
			  }
			  this.websocket.close = ()=>{
			    console.log("关闭")
			  
			  }
			  
			  
			  
			}else{
			  alert("no support")
			}
		
		     this.initEcharts(0,0,0)			
		}
	}
</script>

<style scoped>
	
	.el-button{
	  border: none;
	 
	}
	
	.control_container{
		
		height: 100vh;
		background-repeat: no-repeat;
		background-size: cover;
	}
	/deep/.el-rate__icon {
	    font-size: 50px;
	}

</style>