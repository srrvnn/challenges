var crypto = require('crypto');

var hash = "f894e71e1551d1833a977df952d0cc9de44a1f9669fbf97d51309a2c6574d5eaa746cdeb9ee1a5dfc771d280d33e5672bf024973657c99bf80cb242d493d5bacc771b3b0b422d5c13595cf3e73cfb1df91caedee7a6c5f3ce2c283564a39c52d3306d60cbc0e3e33d7ed01e780acb1ccd9174cfea4704eb233b0f06e52f6d5aba5a5a89e6122dd55f8efcf024961c1003d116007775d60a0d5781d2e35d747b5dece2e0e3d79d272e40c8c66555f5525";

var arrExtras = ["0","1","2","3","4","5","6","7","8","9","_", ".", "@"];

for (var i = 0; i < hash.length; i+=32) {

	var found = false;
	var subHash = hash.substring(i, i+32);

	for (var x = 0; x < 39; x++) {

		var c1 = (x < 26) ? String.fromCharCode(97 + x) : arrExtras[x - 26];

		for (var y = 0; y < 39; y++) {

			var c2 = (y < 26) ? String.fromCharCode(97 + y) : arrExtras[y - 26];

			var oneHash = crypto.createHash('md5').update(c1+c2).digest('hex');
			var twoHash = crypto.createHash('md5').update(oneHash+c1+c2+oneHash).digest('hex');

			if (twoHash == subHash) {

				found = true;
				process.stdout.write(c1 + c2);
			}

			if(found) break;
		}

		if(found) break;
	}
}