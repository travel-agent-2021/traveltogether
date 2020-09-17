// Chart.js scripts
// -- Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

$(document).ready(function () {

    getAgencies();
    initCharts(-1);
    initAgeChart();

});

function initCharts(agencyId) {
    clearChart();
    /*clearChart("monthlyChart");
    clearChart("bestsellersChart");
    clearChart("mostClickedChart");
    clearChart("dailyPriceSum");
    clearChart("monthlyPriceSum");*/
    initDailyChart(agencyId);
    initMonthlyChart(agencyId);
    initBestsellers(agencyId);
    initMostClicked(agencyId);
    initDailyPriceSum(agencyId);
    initMonthlyPriceSum(agencyId);
}

function clearChart() {
    $("#dailyChart").remove();
    let dom = '<canvas id="dailyChart" width="100%" height="30"></canvas>';
    $("#dailyChartContainer").append($(dom));

    $("#monthlyChart").remove();
    dom = '<canvas id="monthlyChart" width="100%" height="50"></canvas>';
    $("#monthlyChartContainer").append($(dom));

    $("#bestsellersChart").remove();
    dom = '<canvas id="bestsellersChart" width="100%" height="30"></canvas>';
    $("#bestsellersChartContainer").append($(dom));

    $("#mostClickedChart").remove();
    dom = '<canvas id="mostClickedChart" width="100%" height="50"></canvas>';
    $("#mostClickedChartContainer").append($(dom));

    $("#dailyPriceSumChart").remove();
    dom = '<canvas id="dailyPriceSum" width="100%" height="30"></canvas>';
    $("#dailyPriceSumContainer").append($(dom));

    $("#monthlyPriceSum").remove();
    dom = '<canvas id="monthlyPriceSum" width="100%" height="50"></canvas>';
    $("#monthlyPriceSumContainer").append($(dom));
}

function initDailyChart(chartAgencyId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/getChartData",
        data: { "agencyId": chartAgencyId},
        xhrFields: {withCredentials: true},
        success: function (data) {
            if (data.status === "success") {
                let resultMap = data.data;
                loadDailyChart(resultMap);
            } else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            // alert("获取信息失败, " + data.responseText);
        }
    })
}

function loadDailyChart(data) {
    //alert(data.length);
    let max = 0;
    let dataKeys = [];//存放key
    let dataValues = [];//存放value
    for (let key in data) { //便历每一条数据
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

function initMonthlyChart(chartAgencyId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/getMonthlyData",
        data: { "agencyId": chartAgencyId},
        xhrFields: {withCredentials: true},
        success: function (data) {
            if (data.status === "success") {
                let resultMap = data.data;
                loadMonthlyChart(resultMap);
            } else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            // alert("获取信息失败, " + data.responseText);
        }
    });
}

function loadMonthlyChart(data) {
    let max = 0;
    let dataKeys = [];
    let dataValues = [];
    for (let key in data) { //便历每一条数据
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


function initBestsellers(chartAgencyId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/getBestsellers",
        data: { "agencyId": chartAgencyId},
        xhrFields: {withCredentials: true},
        success: function (data) {
            if (data.status === "success") {
                let resultMap = data.data;
                loadBestsellers(resultMap);
            } else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            // alert("获取信息失败, " + data.responseText);
        }
    })
}

function loadBestsellers(data) {
    //alert(data.length);
    let max = 0;
    let dataKeys = [];//存放key
    let dataValues = [];//存放value
    for (let i = 0; i < data.length; i++) {
        for (let key in data[i]) {
            dataKeys.push(key);
            dataValues.push(data[i][key]);
            if (max < data[i][key]) {
                max = data[i][key];
            }
        }
    }
    max = Math.ceil(max * 1.2);

    var ctx = document.getElementById("bestsellersChart");
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

function initMostClicked(chartAgencyId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/getClickTimes",
        data: { "agencyId": chartAgencyId},
        xhrFields: {withCredentials: true},
        success: function (data) {
            if (data.status === "success") {
                let resultMap = data.data;
                loadMostClicked(resultMap);
            } else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            // alert("获取信息失败, " + data.responseText);
        }
    });
}

function loadMostClicked(data) {
    let max = 0;
    let dataKeys = [];
    let dataValues = [];
    for (let i = 0; i < data.length; i++) {
        for (let key in data[i]) {
            dataKeys.push(key);
            dataValues.push(data[i][key]);
            if (max < data[i][key]) {
                max = data[i][key];
            }
        }
    }
    max = Math.ceil(max * 1.2);
    var ctx = document.getElementById("mostClickedChart");
    var myLineChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: dataKeys,
            datasets: [{
                label: "次数",
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


function initDailyPriceSum(chartAgencyId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/getDailyPriceSum",
        data: { "agencyId": chartAgencyId},
        xhrFields: {withCredentials: true},
        success: function (data) {
            if (data.status === "success") {
                let resultMap = data.data;
                loadDailyPriceSum(resultMap);
            } else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            // alert("获取信息失败, " + data.responseText);
        }
    })
}

function loadDailyPriceSum(data) {
    //alert(data.length);
    let max = 0;
    let dataKeys = [];//存放key
    let dataValues = [];//存放value
    for (let key in data) { //便历每一条数据
        dataKeys.push(key);
        dataValues.push(data[key]);
        if (data[key] > max) {
            max = data[key];
        }
    }
    max = Math.ceil(max * 1.2);

    var ctx = document.getElementById("dailyPriceSum");
    var myLineChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: dataKeys,
            datasets: [{
                label: "元",
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

function initMonthlyPriceSum(chartAgencyId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/getMonthlyPriceSum",
        data: { "agencyId": chartAgencyId},
        xhrFields: {withCredentials: true},
        success: function (data) {
            if (data.status === "success") {
                let resultMap = data.data;
                loadMonthlyPriceSum(resultMap);
            } else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            // alert("获取信息失败, " + data.responseText);
        }
    });
}

function loadMonthlyPriceSum(data) {
    let max = 0;
    let dataKeys = [];
    let dataValues = [];
    for (let key in data) { //便历每一条数据
        dataKeys.push(key);
        dataValues.push(data[key]);
        if (data[key] > max) {
            max = data[key];
        }
    }
    max = Math.ceil(max * 1.2);
    var ctx = document.getElementById("monthlyPriceSum");
    var myLineChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: dataKeys,
            datasets: [{
                label: "元",
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





function getAgencies() {
    var agencyList = [];
    $.ajax(
        {
            type: "GET",
            url: "http://localhost:8080/agency/getAllAgencies",
            xhrFields: {withCredentials: true},
            success: function (data) {
                if (data.status === "success") {
                    agencyList = data.data;
                    loadAgencies(agencyList);
                } else {
                    alert("获取信息失败01，" + data.data.errMsg);
                }
            },
            error: function (data) {
                alert("获取信息失败02, " + data.responseText);
            }
        }
    )

}
function loadAgencies(agencyList) {
    if (agencyList === "" || agencyList == null) {
        return;
    }
    for (var i = 0; i < agencyList.length; i++) {
        var agency = agencyList[i];
        var agencyName = agency.agencyTitle;
        var agencyID = agency.agencyId;

        var dom = '<option value = "' + agencyID + '">' + agencyName + '</option>';
        $("#selectAgency").append($(dom));
    }
}


function initAgeChart() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/user/getAgeData",
        xhrFields: {withCredentials: true},
        success: function (data) {
            if (data.status === "success") {
                let resultMap = data.data;
                loadAgeChart(resultMap);
            } else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            // alert("获取信息失败, " + data.responseText);
        }
    });
}

function loadAgeChart(data) {
    let dataKeys = [];
    let dataValues = [];
    for (let key in data) { //便历每一条数据
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

function selectAgency() {
    let agencyId = $("#selectAgency").val();
    initCharts(agencyId);
}