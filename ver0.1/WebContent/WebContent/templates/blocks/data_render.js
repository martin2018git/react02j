var discountCellTemplate = function(container, options) {
    $("<div/>").dxBullet({
        onIncidentOccurred: null,
        size: {
            width: 150,
            height: 35
        },
        margin: {
            top: 5,
            bottom: 0,
            left: 5
        },
        showTarget: false,
        showZeroLevel: true,
        value: options.value * 100,
        startScaleValue: 0,
        endScaleValue: 100,
        tooltip: {
            enabled: true,
            font: {
                size: 18
            },
            paddingTopBottom: 2,
            customizeTooltip: function() {
                return { text: options.text };
            },
            zIndex: 5
        }
    }).appendTo(container);
};

var collapsed = false;   

//document.getElementById('_JS_ELEMENT_ID').innerHTML = "<div class='demo-container dx-viewport'><div id='_JS_ELEMENT_ID-gridContainer'></div></div>";
document.getElementById('_JS_ELEMENT_ID').innerHTML = "<div id='_JS_ELEMENT_ID-gridContainer'></div>";


$("#_JS_ELEMENT_ID-gridContainer").dxDataGrid({
        dataSource: {
            store: {
                type: "_JS_SOURCE_TYPE",
                url: "_JS_SOURCE_URI",
                beforeSend: function(request) {
                    request.params.startDate = "2018-05-10";
                    request.params.endDate = "2018-05-15";
                }
            }
        },
        paging: {
            pageSize: 10
        },
        pager: {
            showPageSizeSelector: true,
            allowedPageSizes: [10, 20, 30]
        },
        remoteOperations: false,
        searchPanel: {
            visible: true,
            highlightCaseSensitive: true
        },
        groupPanel: { visible: true },
        grouping: {
            autoExpandAll: false
        },
        allowColumnReordering: true,
        rowAlternationEnabled: true,
        showBorders: true,
        columns: [
        	_JS_SOURCE_FIELDS
        	]
    });
