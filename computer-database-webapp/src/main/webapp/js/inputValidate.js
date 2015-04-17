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

$(document).ready(function() {
	// overidd
	$.validator.methods.date = function(value, element) {
		if (strings['language'] === 'fr')
			return this.optional(element) || isFrValidDate(value) !== false;
		else
			return this.optional(element) || isUsValidDate(value) !== false;
	}

	jQuery.validator.addMethod("regex", function(value, element, regexp) {
		if (regexp.constructor != RegExp)
			regexp = new RegExp(regexp);
		else if (regexp.global)
			regexp.lastIndex = 0;
		return this.optional(element) || regexp.test(value);
	}, strings['date']);

	$("#formAddEditComputer").validate({

		// form validation rules
		rules : {
			computerName : {
				required : true
			},
			introduced : {
				required : false,
				"regex" : strings['date.regex']
			},
			discontinued : {
				required : false,
				"regex" : strings['date.regex']
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
});

/**
 * For frensh pattern in computer-database project
 * 
 * @param dateString
 * @returns {Boolean}
 */
function isFrValidDate(dateString) {
	// First check for the pattern
	if (!/^(0[1-9]|1[0-9]|2[0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)[0-9][0-9]$/
			.test(dateString))
		return false;

	// Parse the date parts to integers
	var parts = dateString.split("-");
	var day = parseInt(parts[0], 10);
	var month = parseInt(parts[1], 10);
	var year = parseInt(parts[2], 10);

	// Check the ranges of month and year
	if (year < 1000 || year > 3000 || month == 0 || month > 12)
		return false;

	var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

	// Adjust for leap years
	if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
		monthLength[1] = 29;

	// Check the range of the day
	return day > 0 && day <= monthLength[month - 1];
};

/**
 * For US pattern in computer-database project
 * 
 * @param dateString
 * @returns {Boolean}
 */
function isUsValidDate(dateString) {
	// First check for the pattern
	if (!/^(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[01])-(19|20)[0-9][0-9]$/
			.test(dateString))
		return false;

	// Parse the date parts to integers
	var parts = dateString.split("-");
	var day = parseInt(parts[1], 10);
	var month = parseInt(parts[0], 10);
	var year = parseInt(parts[2], 10);

	// Check the ranges of month and year
	if (year < 1000 || year > 3000 || month == 0 || month > 12)
		return false;

	var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

	// Adjust for leap years
	if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
		monthLength[1] = 29;

	// Check the range of the day
	return day > 0 && day <= monthLength[month - 1];
};
