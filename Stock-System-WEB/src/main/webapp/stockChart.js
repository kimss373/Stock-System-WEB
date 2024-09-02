document.addEventListener('DOMContentLoaded', function () {
	console.log(dataFromJSP);
	let data1 = [];
	for (let i = 0; i < dataFromJSP.length; i++) {
	  // 데이터 추가
	  data1.push({ x: dataFromJSP[i].basDt, y: [dataFromJSP[i].mkp, dataFromJSP[i].hipr, dataFromJSP[i].lopr, dataFromJSP[i].clpr] });
	}
    var options = {
        series: [{
            data: data1
        }],
        chart: {
            type: 'candlestick',
            height: 500
        },
        xaxis: {
            type: 'datetime',
            labels:{
				show:false
			}
        },
        yaxis: {
            tooltip: {
                enabled: true
            }
        },
        plotOptions: {
            candlestick: {
                colors: {
                    upward: '#FF4500',
                    downward: '#00BFFF'
                }
            }
        },
        tooltip: {
            enabled: true,
            shared: false,
            custom: function({ series, seriesIndex, dataPointIndex, w }) {
                const o = w.globals.seriesCandleO[seriesIndex][dataPointIndex];
                const h = w.globals.seriesCandleH[seriesIndex][dataPointIndex];
                const l = w.globals.seriesCandleL[seriesIndex][dataPointIndex];
                const c = w.globals.seriesCandleC[seriesIndex][dataPointIndex];
                return `
                    <div>
                        <strong>Date: ${new Date(w.globals.labels[dataPointIndex]).toLocaleDateString()}</strong><br>
                        시가: ${o}<br>
                        고가: ${h}<br>
                        저가: ${l}<br>
                        종가: ${c}<br>
                    </div>
                `;
            }
        }
    };

    var chart = new ApexCharts(document.querySelector("#chart"), options);
    chart.render();
});
