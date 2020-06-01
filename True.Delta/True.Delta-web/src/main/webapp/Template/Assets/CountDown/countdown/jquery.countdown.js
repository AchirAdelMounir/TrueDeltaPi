/**
 * @name		jQuery Countdown Plugin
 * @author		Martin Angelov
 * @version 	1.0
 * @url			http://tutorialzine.com/2011/12/countdown-jquery/
 * @license		MIT License
 */

(function($){
	
	// Number of seconds in every time division
	
   
    

// Number of seconds in every time division
	var days	= 24*60*60,
	hours	= 60*60,
	minutes	= 60;
  
    

   
   
	
	// Creating the plugin
	$.fn.countdown = function(prop){
		
		var options = $.extend({
			callback	: function(){},
			timestamp	: 0
		},prop);
		
		var left, d, h, m, s, positions;

		// Initialize the plugin
		init(this, options);
		
		positions = this.find('.position');
		
		(function tick(){ var start = new Date;
		start.setHours(9, 30, 0);
			
			 var now = new Date;
			  if (now > start) { // too late, go to tomorrow
			    start.setDate(start.getDate() + 1);
			  }
			  var remain = ((start - now) / 1000);
			   h = pad((remain / 60 / 60) % 60);
			   updateDuo(2, 3, h);
			  
			   m = pad((remain / 60) % 60);
			   updateDuo(4, 5, m);
			   s = pad(remain % 60);
			   updateDuo(6, 7, s);
			   d=0;
			  
			 
			
			// Calling an optional user supplied callback
			  options.callback(d, h, m, s);
			  setTimeout(tick, 1000);
				
				// Scheduling another call of this function in 1s
				
		})();
		
		// This function updates two digit positions at once
		function updateDuo(minor,major,value){
			switchDigit(positions.eq(minor),Math.floor(value/10)%10);
			switchDigit(positions.eq(major),value%10);
		}
		
		return this;
	};


	function init(elem, options){
		elem.addClass('countdownHolder');

		// Creating the markup inside the container
		$.each(['0','hh','mm','ss'],function(i){
			$('<span class="count'+this+'">').html(
				'<span class="position">\
					<span class="digit static">0</span>\
				</span>\
				<span class="position">\
					<span class="digit static">0</span>\
				</span>'
			).appendTo(elem);
			
			if(this!="Seconds"){
				elem.append('<span class="countDiv countDiv'+i+'"></span>');
			}
		});

	}

	// Creates an animated transition between the two numbers
	function switchDigit(position,number){
		
		var digit = position.find('.digit')
		
		if(digit.is(':animated')){
			return false;
		}
		
		if(position.data('digit') == number){
			// We are already showing this number
			return false;
		}
		
		position.data('digit', number);
		
		var replacement = $('<span>',{
			'class':'digit',
			css:{
				top:'-2.1em',
				opacity:0
			},
			html:number
		});
		
		// The .static class is added when the animation
		// completes. This makes it run smoother.
		
		digit
			.before(replacement)
			.removeClass('static')
			.animate({top:'2.5em',opacity:0},'fast',function(){
				digit.remove();
			})

		replacement
			.delay(100)
			.animate({top:0,opacity:1},'fast',function(){
				replacement.addClass('static');
			});
	}
})(jQuery);



 // 11pm

function pad(num) {
  return ("0" + parseInt(num)).substr(-2);
}

