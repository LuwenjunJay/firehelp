<template>
	<div style="width:100%;">
	  <h2 style="text-align: center">火场情况采集</h2>
	  <div style="width:90%;height:300px;margin: auto;">
		  <div style="width: 100%;display: flex;align-items:center;padding: 30px;">
		  <h2>偏航角：</h2>
		  <div style="width:40%;height:100px;background:black;
		  border:5px solid rgba(125,125,125,1);display:flex;justify-content:center;align-items: center;">
			  <h1 style="color:green;font-size:5vw;">{{pianhangjiao}} °</h1>
			  
			  </div>

		  </div>
		  
		  <div style="width: 100%;display: flex;justify-content: space-around;margin-top: 30px;">
			  
			 <div style="width: 45%;">
			  <div style="width: 100%;display: flex;align-items: center;">
		  <h2>实时温度：</h2>
		<div style="width:70%;height:100px;background:black;
		border:5px solid rgba(125,125,125,1);display:flex;justify-content:center;align-items: center;">
					  <h1 style="color:green;font-size:5vw;">{{temperature}} °C</h1>
					  
					  </div>
		  
		  </div>
		  
		  <div style="width: 100%;height: 300px;" id="main1">
			</div>  
		    <p style="text-align: center;">本次温度采集情况</p>
		  </div>
		  <div style="width: 45%;">
			  <div style="width:100%;display: flex;align-items: center;">
		  <h2>实时气敏：</h2>
		  <div style="width:70%;height:100px;background:black;
		  border:5px solid rgba(125,125,125,1);display:flex;justify-content:center;align-items: center;">
		  			  <h1 style="color:green;font-size:5vw;">{{qimin}}</h1>
		  			  
		  			  </div>
		  
		  </div>
		  <div id="main2" style="width: 100%;height: 300px;">
			  
		  </div>
		  <p style="text-align: center;">本次气敏采集情况</p>
		   </div>
		  </div>
		  
	  </div>
	
	
	</div>
</template>

<script>
	import * as echarts from 'echarts';
	
	
		
	
	
	export default {
		data(){
			return {
			temperature:0,
            pianhangjiao:0,
			qimin:0,
			websocket:null,
			temX:[],
			temY:[],
			qiminX:[],
			qiminY:[]
			}
		},
		methods:{
			initEcharts(){
				
				var mychart2 = echarts.init(document.getElementById("main2"))
				var myChart = echarts.init(document.getElementById('main1'));
				
				
				
				
				
				const option2 = {
				  xAxis: {
				    type: 'category',
				    data: this.qiminX
				  },
				  yAxis: {
				    type: 'value'
				  },
				  series: [
				    {
				      data: this.qiminY,
				      type: 'line',
				      symbol: 'triangle',
				      symbolSize: 20,
				      lineStyle: {
				        color: '#5470C6',
				        width: 4,
				        type: 'dashed'
				      },
				      itemStyle: {
				        borderWidth: 3,
				        borderColor: '#EE6666',
				        color: 'yellow'
				      }
				    }
				  ]
				};
			
				
				
				const option = {
				  xAxis: {
				    type: 'category',
				    data: this.temX
				  },
				  yAxis: {
				    type: 'value'
				  },
				  series: [
				    {
				      data: this.temY,
				      type: 'line'
				    }
				  ]
				};
				
				
				myChart.setOption(option)
				mychart2.setOption(option2)
			},
			
	
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
				  
				  var data = event.data.split(",")
				  this.temperature = data[4]
				  this.temX.push(new Date().toLocaleString())
				  this.temY.push(this.temperature)
				  this.qimin = data[3]
				  this.qiminX.push(new Date().toLocaleString())
				  this.qiminY.push(this.qimin)
				  this.pianhangjiao = data[5]
				  
				  this.initEcharts()
				 
							
			  }
			  this.websocket.close = ()=>{
			    console.log("关闭")
			  
			  }
			  
			  
			  
			}else{
			  alert("no support")
			}
			
			
			this.initEcharts()
             			
		}
			
	}
	
</script>

<style>

</style>