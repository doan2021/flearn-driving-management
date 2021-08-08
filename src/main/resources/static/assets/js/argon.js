'use strict';
var Popover = (function() {
	// Variables
	var $popover = $('[data-toggle="popover"]'),
		$popoverClass = '';
	// Methods
	function init($this) {
		if ($this.data('color')) {
			$popoverClass = 'popover-' + $this.data('color');
		}
		var options = {
			trigger: 'focus',
			template: '<div class="popover ' + $popoverClass + '" role="tooltip"><div class="arrow"></div><h3 class="popover-header"></h3><div class="popover-body"></div></div>'
		};
		$this.popover(options);
	}
	// Events
	if ($popover.length) {
		$popover.each(function() {
			init($(this));
		});
	}
})();

//
// Tooltip
//
'use strict';
var Tooltip = (function() {
	// Variables
	var $tooltip = $('[data-toggle="tooltip"]');
	// Methods
	function init() {
		$tooltip.tooltip();
	}
	// Events
	if ($tooltip.length) {
		init();
	}
})();

//
// Form control
//
'use strict';
var FormControl = (function() {
	// Variables
	var $input = $('.form-control');
	// Methods
	function init($this) {
		$this.on('focus blur', function(e) {
        $(this).parents('.form-group').toggleClass('focused', (e.type === 'focus'));
    }).trigger('blur');
	}
	// Events
	if ($input.length) {
		init($input);
	}
})();

//
// Bootstrap Datepicker
//
'use strict';
var Datepicker = (function() {
	// Variables
	var $datepicker = $('.datepicker');
	// Methods
	function init($this) {
		var options = {
			language: 'en',
			format: 'dd/mm/yyyy',
			disableTouchKeyboard: true,
			autoclose: false,
			showOnFocus : true,
			todayHighlight: true,
			todayBtn: true
		};
		$this.datepicker(options);
	}
	// Events
	if ($datepicker.length) {
		$datepicker.each(function() {
			init($(this));
		});
	}
})();

'use strict';
var noUiSlider = (function() {
	if ($(".input-slider-container")[0]) {
			$('.input-slider-container').each(function() {
					var slider = $(this).find('.input-slider');
					var sliderId = slider.attr('id');
					var minValue = slider.data('range-value-min');
					var maxValue = slider.data('range-value-max');
					var sliderValue = $(this).find('.range-slider-value');
					var sliderValueId = sliderValue.attr('id');
					var startValue = sliderValue.data('range-value-low');
					var c = document.getElementById(sliderId),
							d = document.getElementById(sliderValueId);
					noUiSlider.create(c, {
							start: [parseInt(startValue)],
							connect: [true, false],
							//step: 1000,
							range: {
									'min': [parseInt(minValue)],
									'max': [parseInt(maxValue)]
							}
					});
					c.noUiSlider.on('update', function(a, b) {
							d.textContent = a[b];
					});
			})
	}
	if ($("#input-slider-range")[0]) {
			var c = document.getElementById("input-slider-range"),
					d = document.getElementById("input-slider-range-value-low"),
					e = document.getElementById("input-slider-range-value-high"),
					f = [d, e];

			noUiSlider.create(c, {
					start: [parseInt(d.getAttribute('data-range-value-low')), parseInt(e.getAttribute('data-range-value-high'))],
					connect: !0,
					range: {
							min: parseInt(c.getAttribute('data-range-value-min')),
							max: parseInt(c.getAttribute('data-range-value-max'))
					}
			}), c.noUiSlider.on("update", function(a, b) {
					f[b].textContent = a[b]
			})
	}
})();
