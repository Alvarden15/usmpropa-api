google.charts.load('current', {'packages':['corechart']});

google.charts.setOnLoadCallback(graficoPrincipal);

function graficoPrincipal() {
      $.ajax({
        url: "https://usmpropa-api.herokuapp.com/api/ropa/dashboard/tipos",
        dataType: "json",
      }).done(function (jsonData) {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'grupo');
        data.addColumn('number', 'dashTotal');

        jsonData.forEach(function (row) {
          data.addRow([
            row.grupo,
            row.dashTotal
          ]);
        });

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
        chart.draw(data, {
          width: 400,
          height: 240
        });
      }).fail(function (jq, text, err) {
        console.log(text + ' - ' + err);
      });
}
