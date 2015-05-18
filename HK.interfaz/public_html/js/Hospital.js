(function () {
    var hospitalKennedy = angular.module('hospitalKennedy', []);
    var pacienteActual;
    hospitalKennedy.directive('toolbar', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/toolbar.html',
            controller: function () {
                this.tab = 0;
                this.selectTab = function (setTab) {
                    this.tab = setTab;
                };
                this.isSelected = function (tabParam) {
                    return this.tab === tabParam;
                };
            },
            controllerAs: 'toolbar'
        };
    });
    hospitalKennedy.directive('pacienteInfo', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/pacienteInfo.html',
            controller: ['$http', function ($http) {
                    var self = this;
                    console.log("Put a message here.");
                    self.pacientes = [];
                    self.pacientes = [{id: '12345678', nombre: 'Yolo', edad: '19', altura: '50'}, {id: '87654321', nombre: 'Yolo2', edad: '17', altura: '148'}];
                    $http.get('http://localhost:8080/hospitalKennedy.servicios/webresources/Doctor/paciente').success(function (data) {
                        self.pacientes = data;
                    });
                    console.log('Se obtuvo el resultado ' + pacientes);
                }],
            controllerAs: 'getPacientes'
        };
    });
    hospitalKennedy.directive('nuevoPaciente', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/nuevo-paciente.html',
            controller: ['$http', function ($http) {
                    var self = this;
                    self.paciente = {};
                    this.addPaciente = function () {

                        console.log("::::::::::::::::::::::::::::----------------:::::::::::::::::::: ENTRO AL METODO PARA AGREGAR PACIENTE");

                        $http.post('http://localhost:8080/hospitalKennedy.servicios/webresources/Doctor/agregarPaciente', JSON.stringify(self.paciente)).success(function (data) {
                            console.log("HIZO EL METODO :) :) :) :) :) :) :) ")
                            self.paciente = {};
                            toolbar.tab = 0;
                        });
                    };
                }],
            controllerAs: 'agregarPaciente'
        };
    });
    hospitalKennedy.directive('reportes', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/reportes.html',
            controller: ['$http', function ($http) {
                    var self = this;
                    console.log("Se busca los reportes del señor: " + idPaciente);
                    self.reportesPacientes = [];
                    self.reporte = {};
                    this.darReportesDeId = function (id) {
                        console.log("Busca los reportes del id " + id);
                        //console.log('http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/'+id+'/reportes/');
                        self.reportesPacientes = [{id: '87654321', actividadFisica: 'Yoloismo', alimentacion: "Carne", gravedad: "gravisima", fechaCreacion: "2015/04/03", localizacionDolor: "detras de la cabeza", patronSuenio: "poco sueño", medicamentosRecientes: "Vicodin"}, {id: '12345678', actividadFisica: 'Yoloismo', alimentacion: "Carne", gravedad: "gravisima", fechaCreacion: "2015/04/03", localizacionDolor: "detras de la cabeza", patronSuenio: "poco sueño", medicamentosRecientes: "Vicodin"}];
                        $http.get('http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/' + id + '/reportes/').success(function (data) {
                            self.reportesPacientes = data;
                        });
                        return self.reportesPacientes;
                    };
                    this.darReportesEntreFechas = function (id, fecha1, fecha2) {
                        console.log('Yooooolooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo');
                        self.reportesPacientes = [{id: '12345678', actividadFisica: 'Yoloismo', alimentacion: "Carne", gravedad: "gravisima", fechaCreacion: "2015/04/03", localizacionDolor: "detras de la cabeza", patronSuenio: "poco sueño", medicamentosRecientes: "Vicodin"}];
                        $http.get('http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/' + id + '/reportes/' + fecha1 + '/' + fecha2).success(function (data) {
                            self.reportesPacientes = data;
                        });
                        return self.reportesPacientes;
                    };
                    this.darDetalles = function (idPaciente, idReporte) {
                        console.log('entroo a dar detalles del paciente: ' + idPaciente + ' sobre el reporte: ' + idReporte);
                        $http.get('http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/' + idPaciente + '/reportes/' + idReporte).success(function (data) {
                            self.reporte = data;
                        });
                        self.reportesPacientes = [{id: '12345678', actividadFisica: 'Yoloismo', alimentacion: "Carne", gravedad: "gravisima", fechaCreacion: "2015/04/03", localizacionDolor: "detras de la cabeza", patronSuenio: "poco sueño", medicamentosRecientes: "Vicodin"}];
                    };
                    this.darReporte = function () {
                        return self.reporte;
                    };
                }],
            controllerAs: 'getReportes'
        };
    });
    hospitalKennedy.controller('pacientesActuales', function () {
        var pacientes;
        console.log("Entro al metodo, al menos");
        function asignarPacienteActual(nPaciente) {
            pacienteActual = nPaciente;
            console.log("HUEHUEHUEHUEHUEHUE");
        }
        ;
        this.asignarPacientes = function (nPacientes) {
            pacientes = nPacientes;
        };
        this.darActual = function () {
            return pacienteActual;
        };
    });
    hospitalKennedy.directive('nuevoReporte', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/nuevo-reporte.html',
            controller: ['$http', function ($http) {
                    var self = this;
                    self.reporte = {};
                    this.addReporte = function (id) {
                        console.log("Entra a metodo de agregar Reporte");
                        $http.post('http://localhost:8080/hospitalKennedy.servicios/webresources/Pacientes/10203040/agregarReportes/', JSON.stringify(self.reporte)).success(function (data) {
                            console.log("Metodo check");
                            self.reporte = {};
                            toolbar.tab = 0;
                        });
                    };
                }],
            controllerAs: 'agregarReporte'
        };
    });
    hospitalKennedy.directive('detallesReporte', function () {
        return{
            restrict: 'E',
            templateUrl: 'partials/detallesReporte.html',
            controller: ['$http', function ($http) {
                    var self = this;
                    self.reporte = [];
                    self.reporte = [{id: '12345678', actividadFisica: 'Yoloismo', alimentacion: "Carne", gravedad: "gravisima", fechaCreacion: "2015/04/03", localizacionDolor: "detras de la cabeza", patronSuenio: "poco sueño", medicamentosRecientes: "Vicodin"}];
                    self.reporte = hospitalKennedy.getReportes.darReporte;
                    console.log('El reporte a dar detalles es ' + self.reporte);

                }],
            controllerAs: 'detallesReporte'
        };
    });
})();
