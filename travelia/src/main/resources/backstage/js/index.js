
// Chart.js scripts
// -- Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

$(document).ready(function () {
    initDailyChart();
    initMonthlyChart();
    initAgeChart();
});

function initDailyChart() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/getChartData",
        data: { "agencyId": -1},
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                let resultMap = data.data;
                loadDailyChart(resultMap);
            }else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            // alert("获取信息失败, " + data.responseText);
        }
    })
}

function loadDailyChart(data) {
    //alert(data.length);
    let max = 0;
    let dataKeys= [];//存放key
    let dataValues= [];//存放value
    for(let key in data) { //便历每一条数据
        dataKeys.push(key);
        dataValues.push(data[key]);
        if (data[key] > max) {
            max = data[key];
        }
    }
    max = Math.ceil(max * 1.2);

    var ctx = document.getElementById("dailyChart");
    var myLineChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: dataKeys,
            datasets: [{
                label: "件",
                lineTension: 0.3,
                backgroundColor: "rgba(2,117,216,0.2)",
                borderColor: "rgba(2,117,216,1)",
                pointRadius: 5,
                pointBackgroundColor: "rgba(2,117,216,1)",
                pointBorderColor: "rgba(255,255,255,0.8)",
                pointHoverRadius: 5,
                pointHoverBackgroundColor: "rgba(2,117,216,1)",
                pointHitRadius: 20,
                pointBorderWidth: 2,
                data: dataValues,
            }],
        },
        options: {
            scales: {
                xAxes: [{
                    time: {
                        unit: 'date'
                    },
                    gridLines: {
                        display: false
                    },
                    ticks: {
                        maxTicksLimit: 7
                    }
                }],
                yAxes: [{
                    ticks: {
                        min: 0,
                        max: max,
                        maxTicksLimit: 5
                    },
                    gridLines: {
                        color: "rgba(0, 0, 0, .125)",
                    }
                }],
            },
            legend: {
                display: false
            }
        }
    });
}

function initMonthlyChart() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/getMonthlyData",
        data: { "agencyId": -1},
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                let resultMap = data.data;
                loadMonthlyChart(resultMap);
            }else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            // alert("获取信息失败, " + data.responseText);
        }
    });
}

function loadMonthlyChart(data) {
    let max = 0;
    let dataKeys= [];
    let dataValues= [];
    for(let key in data) { //便历每一条数据
        dataKeys.push(key);
        dataValues.push(data[key]);
        if (data[key] > max) {
            max = data[key];
        }
    }
    max = Math.ceil(max * 1.2);
    var ctx = document.getElementById("monthlyChart");
    var myLineChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: dataKeys,
            datasets: [{
                label: "件",
                backgroundColor: "rgba(2,117,216,1)",
                borderColor: "rgba(2,117,216,1)",
                data: dataValues
            }],
        },
        options: {
            scales: {
                xAxes: [{
                    time: {
                        unit: 'month'
                    },
                    gridLines: {
                        display: false
                    },
                    ticks: {
                        maxTicksLimit: 6
                    }
                }],
                yAxes: [{
                    ticks: {
                        min: 0,
                        max: max,
                        maxTicksLimit: 5
                    },
                    gridLines: {
                        display: true
                    }
                }],
            },
            legend: {
                display: false
            }
        }
    });
}

function initAgeChart() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/user/getAgeData",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                let resultMap = data.data;
                loadAgeChart(resultMap);
            }else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            // alert("获取信息失败, " + data.responseText);
        }
    });
}

function loadAgeChart(data) {
    let dataKeys= [];
    let dataValues= [];
    for(let key in data) { //便历每一条数据
        dataKeys.push(key);
        dataValues.push(data[key]);
    }
    var ctx = document.getElementById("ageChart");
    var myPieChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: dataKeys,
            datasets: [{
                data: dataValues,
                backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745'],
            }],
        },
    });
}

