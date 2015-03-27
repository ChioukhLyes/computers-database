/**
 * jQuery Validation Plugin 1.9.0
 *
 * http://bassistance.de/jquery-plugins/jquery-plugin-validation/
 * http://docs.jquery.com/Plugins/Validation
 *
 * Copyright (c) 2006 - 2011 Jörn Zaefferer
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 */

(function($, W, D) {
	var validationComputersOperations = {};

	validationComputersOperations.UTIL = {
		setupFormValidation : function() {
			//Add custom methode to match date
			$.validator.addMethod("DateValidator", function(value, element) {
				//Because date is not required
				if (!value)
					return true;
				//Regex for my format
				return value.match(/^\d\d\d\d\-\d\d?\-\d\d?$/);
			}, "Please enter a date in the format yyyy-MM-dd.");

			$("#formAddEditComputer").validate({
				//form validation rules
				rules : {
					computerName : "required",
					introduced : {
						required : false,
						DateValidator : true
					},
					discontinued : {
						required : false,
						DateValidator : true
					}
				},
				//form validation messages
				messages : {
					computerName : "Please enter computer name"
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
		}
	}
	//Whene document is ready
	$(D).ready(function($) {
		validationComputersOperations.UTIL.setupFormValidation();
	});

})(jQuery, window, document);