"use strict";

/******************************************************************************************

Expenses controller

******************************************************************************************/

var app = angular.module("expenses.controller", []);

app.controller("ctrlExpenses", ["$rootScope", "$scope", "config", "restalchemy", function ExpensesCtrl($rootScope, $scope, $config, $restalchemy) {
	// Update the headings
	$rootScope.mainTitle = "Expenses";
	$rootScope.mainHeading = "Expenses";

	// Update the tab sections
	$rootScope.selectTabSection("expenses", 0);

	var restExpenses = $restalchemy.init({ root: $config.apiroot }).at("expenses");

	$scope.dateOptions = {
		changeMonth: true,
		changeYear: true,
		dateFormat: "dd/mm/yy"
	};
	
	var vatRate = NaN;
	
	var loadVATRate = function(){
		restExpenses.at("expenses","vatrate").get().then(function(vatResponse){
			vatRate = vatResponse.vatRate;
		});
	}
	
	var loadExpenses = function() {
		// Retrieve a list of expenses via REST
		restExpenses.get().then(function(expenses) {
			$scope.expenses = expenses;
		});
	}
	
	// TODO accept any currency?
	var myreg = /([0-9\.,]+)(?:\s)*(EUR)?/;
	
	// TODO centralize 'N/A' returns
	// TODO create custom angular filter for formatting
	$scope.computeVat = function(amount){
		if(isNaN(vatRate)){
			return 'N/A';
		}
		else if(amount){
			var result = myreg.exec(amount); //Analyze input amount
			if (result && result.length === 3){
				var value = parseFloat(result[1].replace(/,/g,'')) * vatRate; // Numeric part to float
				if (isNaN(value)){
					return 'N/A';
				}
				else{
					var valueStr = value.toFixed(2); // Two decimals
					if (result[2]){ // Append currency symbol is exist
						valueStr += ' '+ result[2];
					}
					return valueStr;
				}
			}
			else{
				return 'N/A';
			}
		}
		else{
			return 'N/A';
		}
	}

	$scope.saveExpense = function() {
		if ($scope.expensesform.$valid) {
			// Post the expense via REST
			restExpenses.post($scope.newExpense).then(function() {
				// Reload new expenses list
				loadExpenses();
			});
		}
	};

	$scope.clearExpense = function() {
		$scope.newExpense = {};
	};

	// Initialise scope variables
	loadVATRate();
	loadExpenses();
	$scope.clearExpense();
}]);
