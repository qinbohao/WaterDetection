/*
 *  Document   : compCharts.js
 *  Author     : pixelcave
 *  Description: Custom javascript code used in Charts page
 */

var CompCharts = function() {

    // Get random number function from a given range
    var getRandomInt = function(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    };

    return {
        init: function() {
  
            var chartClassic    = $('#chart-classic');       
            var chartLive       = $('#chart-live');
            // Data for the charts
            var dataEarnings    = [[0, 0], [1, 0], [2, 0],[3, 0]];
            var dataMonths      = [[0, 'Jan'], [1, 'Feb'], [2, 'Mar'],[3, 'Apr']];
         // var dataEarnings    = [[1, 1900], [2, 2300], [3, 3200], [4, 2500], [5, 4200], [6, 3100], [7, 3600], [8, 2500], [9, 4600], [10, 3700], [11, 4200], [12, 5200]];
            //  var dataMonths      = [[1, 'Jan'], [2, 'Feb'], [3, 'Mar'], [4, 'Apr'], [5, 'May'], [6, 'Jun'], [7, 'Jul'], [8, 'Aug'], [9, 'Sep'], [10, 'Oct'], [11, 'Nov'], [12, 'Dec']];
            var chartLv = $.plot(chartLive, // Initialize live chart
                    [{data: [[0,0],[1,0],[2,0],[3,0]]}],
                    {
                        series: {shadowSize: 0},
                        lines: {show: true, lineWidth: 2, fill: true, fillColor: {colors: [{opacity: .2}, {opacity: .2}]}},
                        points: {show: true, radius: 5},
                        colors: ['#5ccdde'],
                        grid: {borderWidth: 0, color: '#aaaaaa',hoverable: true, clickable: true},
                        yaxis: {show: true, min: 0, max: 110},
                        xaxis: {show: true,min:0,max:3}
                    }
                );
            // Classic Chart
            var charClass=$.plot(chartClassic,
                [
                    {data: dataEarnings,}
                ],
                {
                    colors: ['#5ccdde', '#454e59', '#ffffff'],
                    legend: {show: true, position: 'nw', backgroundOpacity: 0},
                    grid: {borderWidth: 0, hoverable: true, clickable: true},
                    yaxis: {tickColor: '#f5f5f5',min: 0, max: 14},
                    xaxis: {ticks: dataMonths, tickColor: '#f5f5f5'},
                    points: {show: true, radius: 5},
                    lines: {show: true, fill: true, fillColor: {colors: [{opacity: .6}, {opacity: .6}]}}
                }
            );

            // Creating and attaching a tooltip to the classic chart
            var previousPoint = null, ttlabel = null;
            chartClassic.bind('plothover', function(event, pos, item) {
            	
                if (item) {
                    if (previousPoint !== item.dataIndex) {
                        previousPoint = item.dataIndex;

                        $('#chart-tooltip').remove();
                        var x = item.datapoint[0], y = item.datapoint[1];

                        if (item.seriesIndex === 0) {
                            ttlabel = '<strong>' + y + '</strong>';
                        } else if (item.seriesIndex === 1) {
                            ttlabel = '<strong>' + y + '</strong> sales';
                        } else {
                            ttlabel = '<strong>' + y + '</strong> tickets';
                        }

                        $('<div id="chart-tooltip" class="chart-tooltip">' + ttlabel + '</div>')
                            .css({top: item.pageY - 45, left: item.pageX + 5}).appendTo("body").show();
                    }
                }
                else {
                    $('#chart-tooltip').remove();
                    previousPoint = null;
                }
            });  
            var previousPoint_tur = null, ttlabel_tur = null;
            chartLive.bind('plothover', function(event, pos, item) {
                if (item) {
                    if (previousPoint_tur !== item.dataIndex) {
                    	previousPoint_tur = item.dataIndex;

                        $('#chart-tooltip').remove();
                        var x = item.datapoint[0], y = item.datapoint[1];

                        if (item.seriesIndex === 0) {
                        	ttlabel_tur = '<strong>' + y + '</strong>';
                        } 

                        $('<div id="chart-tooltip" class="chart-tooltip">' + ttlabel_tur + '</div>')
                            .css({top: item.pageY - 45, left: item.pageX + 5}).appendTo("body").show();
                    }
                }
                else {
                    $('#chart-tooltip').remove();
                    previousPoint_tur = null;
                }
            });    
            // Live Chart
            function getTurbidityData() { 
            	var dataLive = [];
				$.ajax({
				type:"POST",
				url:"Getdata",
				data:"Sensor=Turbidity&number=1",
				success:function(data){
					var parjson=$.parseJSON(data);
					var sensordata=parjson.Turbidity;
					 for (var i = 0; dataLive.length < sensordata.length;i++) {          
	                    var y = parseFloat(sensordata[i]);
	                    if (y < 0)
	                        y = 0;
	                    if (y > 100)
	                        y = 100;
	                    dataLive.push(y);
	                   
	                }
					 function getsensordata(){
		                	var res = [];
			                for (var i = 0; i < dataLive.length; i++){
			                	res.push([i, dataLive[i]]);
			                }
			                return res;
		                }
	                var result=getsensordata();	         
	                chartLv.setData([result]); 	             
	                chartLv.draw();
					}
				});								
            }
            function getPHData() { 
            	var dataLive = [];
				$.ajax({
				type:"POST",
				url:"Getdata",
				data:"Sensor=PH&number=2",
				success:function(data){
					var parjson=$.parseJSON(data);
					var sensordata=parjson.PH;
					 for (var i = 0; dataLive.length < sensordata.length;i++) {          
	                    var y = parseFloat(sensordata[i]);
	                    if (y < 0)
	                        y = 0;
	                    if (y > 14)
	                        y = 14;
	                    dataLive.push(y);
	                   
	                }
					 function getsensordata(){
		                	var res = [];
			                for (var i = 0; i < dataLive.length; i++){
			                	res.push([i, dataLive[i]]);
			                }
			                return res;
		                }
	                var result=getsensordata();	            
	                charClass.setData([result]);
	                charClass.draw();
					}
				});								
            }
            function updateChartLive() { // Update live chart
            	getTurbidityData();
            	getPHData();
                setTimeout(updateChartLive, 7000);
            }
            updateChartLive(); // Start getting new data
        }
    };
}();