$(function() {

    Morris.Area({
        element: 'morris-area-chart',
        data: [{
            period: '2016-03',
            electric: 2647
        }, {
            period: '2016-06',
            electric: 2441
        }, {
            period: '2016-09',
            electric: 2501
        }, {
            period: '2016-12',
            electric: 5689
        }, {
            period: '2017-03',
            electric: 2293
        }, {
            period: '2017-06',
            electric: 1881
        }, {
            period: '2017-09',
            electric: 1588
        }, {
            period: '2017-12',
            electric: 5175
        }, {
            period: '2018-03',
            electric: 2028
        }, {
            period: '2018-04',
            electric: 1791
        }],
        xkey: 'period',
        ykeys: ['electric'],
        labels: ['electric'],
        pointSize: 4,
        hideHover: 'true',
        resize: true
    });

    Morris.Donut({
        element: 'morris-donut-chart',
        data: [{
            label: "Download Sales",
            value: 12
        }, {
            label: "In-Store Sales",
            value: 30
        }, {
            label: "Mail-Order Sales",
            value: 20
        }],
        resize: true
    });

    Morris.Bar({
        element: 'morris-bar-chart',
        data: [{
            y: '2006',
            a: 100,
            b: 90
        }, {
            y: '2007',
            a: 75,
            b: 65
        }, {
            y: '2008',
            a: 50,
            b: 40
        }, {
            y: '2009',
            a: 75,
            b: 65
        }, {
            y: '2010',
            a: 50,
            b: 40
        }, {
            y: '2011',
            a: 75,
            b: 65
        }, {
            y: '2012',
            a: 100,
            b: 90
        }],
        xkey: 'y',
        ykeys: ['a', 'b'],
        labels: ['Series A', 'Series B'],
        hideHover: 'auto',
        resize: true
    });
});
