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

/**
 * Français Jour/Mois/Année
 *	(24/05/08)
 */

/**
 * USA Mois/Jour/Année (05/24/08)
 */

(function($, W, D) {
	var validationComputersOperations = {};

	validationComputersOperations.UTIL = {
			
			
		setupFormValidation : function() {
			// set up language
			// alert('languge ' +language);
			// Add custom methode to match date
		
			 
			$.validator.addMethod("DateValidatorFr", function(value, element) {
				// Because date is not required
				if (!value)
					return true;
				// Regex for my format
				return value.match(/^\d\d?\-\d\d?\-^\d\d\d\d?$/);
			});

			// Add custom methode to match date
			$.validator.addMethod("DateValidatorEn", function(value, element) {
				// Because date is not required
				if (!value)
					return true;
				// Regex for my format
				return value.match(/^\d\d?\-\d\d?\-\d\d\d\d?$/);
			});
			
			// alert('en inside');
			$("#formAddEditComputer").validate({
				// form validation rules
				rules : {
					computerName : {
						required : true,
						change : true
					},
					introduced : {
						required : false,
						DateValidatorEn : true
					},
					discontinued : {
						required : false,
						DateValidatorEn : true
					}
				},
				// form validation messages
				 messages : {
				 computerName : strings['required'],
				 introduced : strings['date'],
				 discontinued : strings['date']
				 },
				submitHandler : function(form) {
					form.submit();
				}
			});
		}
	}
	// Whene document is ready
	$(D).ready(function($) {
		validationComputersOperations.UTIL.setupFormValidation();
	});

}) (jQuery, window, document);




