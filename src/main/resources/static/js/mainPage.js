// Chart.jsオブジェクト
var chartCanvas;

// 初期処理
$(function () {

    // チャートを表示するイベント設定
    $(".open-chart-dialog").on("click", async function () {
    	
    	// Ajaxでデータを取得
    	let channelId = $(this).attr("channel-id");
        await drowChart(channelId);

        // チャートを表示
        //left = (Window width - Contents Width) / 2
        let leftPosition = (($(window).width() - $("#chart-dialog").outerWidth(true)) / 2);
        $("#chart-dialog").css({ "left": leftPosition + "px" });
        $("#chart-dialog").show(300);
        
    });
    
    // チャートを非表示するイベント設定
    $(".dialog-close").on("click", function () {
        $(this).parents(".dialog").hide(300);
    });
    
	setInterval(updateDropRates, 2000);

});

/**
 * ドロップ率を取得し、描画を更新.
 */
async function updateDropRates() {

	// ドロップ率を取得
	let dropRates = (await getCurrentDropRate()).dropValues;
	// UI変更
	Object.keys(dropRates).forEach(key => updateUiDropRate(key, dropRates));
	
}

/**
 * ドロップ率のUIを更新.
 */
function updateUiDropRate(channelId, mapDropRates) {
	
	$("#dropRateText"+channelId).text(mapDropRates[channelId]+"%");
	$("#conditionText"+channelId).text(getConditionText(mapDropRates[channelId]));	
}

/**
 * コンディションのテキストを取得.
 * @returns condition
 */
function getConditionText(dropRate) {

	let retText = "";
	
	if (dropRate > 25) {
		retText = "Warning";
	} else {
		retText = "Stable";
	}
	
	return retText;
}


/**
 * 現在のドロップ率を取得する.
 * @returns chartData
 */
async function getCurrentDropRate() {

    let resData;
    
    await $.ajax({
        url: 'http://localhost:8080/currentDropData/',
        type: 'GET',
        dataType: 'json',
        timespan: '1000',
    }).done(function (dropRates) {
        resData = dropRates;
    }).fail(function (e) {
        // エラーハンドリング
        console.log('fail:get data of chart');
        return;
    });

    return resData;

}




// ファンクション

/**
 * チャートを描画
 */
async function drowChart(channelId) {

    // チャートデータ取得
    let chartData = await getChartData(channelId);

    // チャート描画
    if (chartCanvas) {
    	chartCanvas.destroy();
    }
    let ctx = document.getElementById("chartCanvas");
    chartCanvas = new Chart(ctx, {
        type: 'line',
        data: {
            labels: chartData.labels,
            datasets: [
                {
                    label: 'loss',
                    data: chartData.values,
                    borderColor: "rgba(255,0,0,1)",
                    backgroundColor: "rgba(0,0,0,0)"
                },
            ],
        },
        options: {
            title: {
                display: false,
            },
            scales: {
                yAxes: [{
                    ticks: {
                        suggestedMax: 40,
                        suggestedMin: 0,
                        stepSize: 10,
                        callback: function (value, index, values) {
                            return value + '%'
                        }
                    }
                }]
            },
        }
    });
};

/**
 * チャートデータを取得する.
 * @returns chartData
 */
async function getChartData(channelId) {

    let resData;
    
    await $.ajax({
        url: 'http://localhost:8080/chartData/' + channelId,
        type: 'GET',
        dataType: 'json',
        timespan: '1000',
    }).done(function (chartData) {
        resData = chartData;
    }).fail(function (e) {
        // エラーハンドリング
        console.log('fail:get data of chart');
        return;
    });

    return resData;

}
