;$.fn.slideInit = function() {
	$(this).each(function() {
		var dots
		var slides
		var g_index = 0;
		var g_tid = 0;
		var to = function(index) {
			slides.each(function() {
				var i = $(this).data('index');
				var w = $(this).width();
				$(this).animate({'left':((i-index)*w)+'px'});
			})
			dots.removeClass('on')
			dots.eq(index).addClass('on')
		};
		dots = $(this).find('.slide-dots li').on('click', function() {
			var i = $(this).data('index');
			to(g_index=i)
			if (g_tid) {
				clearTimeout(g_tid)
				g_tid=setTimeout(frame, 2000);
			}
		})
		slides = $(this).find('.slide-content li').each(function() {
			var w = $(this).width();
			var i = $(this).data('index');
			var left = i*w;
			$(this).css('left', left);
		});
		var s = $(this)
		var frame = function() {
			to(g_index++)
			if (g_index >= s.data('length'))
				g_index = 0;
			g_tid=setTimeout(frame, 2000);
		};
		g_tid=setTimeout(frame, 2000);
	});
};