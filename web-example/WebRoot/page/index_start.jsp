<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<title>抽奖</title>
		<style type="text/css">
			*{margin:0;padding:0;}
			body{font-size:12px;}
			a{color:#333;}
			ul{list-style:none;}
			
			#marquee1{position:absolute;top:80px;left:50px;width:310px;height:45px;overflow:hidden;background:#333;border:2px solid #333;}
			#marquee1 ul li{float:left; padding:0 1px;}
			#marquee1 ul li img{display:block;}

			#marquee2{position:absolute;top:80px;left:400px;width:300px;height:25px;overflow:hidden; background:#EFEFEF;}
			#marquee2 ul li{float:left; padding:0 10px; line-height:25px;}

			#marquee3{position:absolute;top:150px;left:50px;width:60px;height:235px;overflow:hidden;background:#333;border:2px solid #333;}
			#marquee3 ul li{float:left; padding:1px 0;}
			#marquee3 ul li img{display:block;}

			#marquee4{position:absolute;top:150px;left:400px;width:200px;height:200px; overflow:hidden;background:#EFEFEF;}
			#marquee4 ul li{float:left; width:180px; padding:10px; line-height:20px;}
			
			td{height: 60px; text-align: center; color: #000000; font-size:20px; font-weight:900;}
			p{TEXT-ALIGN: center; color: red; font-size:40px; font-weight:900; margin-bottom:30px; }
			/*#cj_head{TEXT-ALIGN: center; color: red; font-size:40px; font-weight:900; margin-bottom:30px; }*/
			#xyzx{TEXT-ALIGN: center; color: #000000; font-size:80px; font-weight:900; margin:100px; }
		</style>
		<script src="js/jquery-1.3.2.min.js"></script>
		<script src="js/jquery.kxbdMarquee.js"></script>
		<script src="js/choujiang.js"></script>
		<script>
			var choujiangList = new Array();
			var currentAwardIndex = 0;
			function init(){
				var cj1 = new ChouJiangObj();
				cj1.totalAward = 60;
				cj1.toAwardPersons = ['0542', '0543', '0544', '0545', '0546', '0547', '0548', '0549', '0550', '0551', '0552', '0553', '0554', '0555', '0556', '0557', '0558', '0559', '0560', '0561', '0562', '0563', '0564', '0565', '0566', '0567', '0568', '0569', '0570', '0571', '0572', '0573', '0574', '0575', '0576', '0577', '0578', '0579', '0580', '0581', '0582', '0583', '0584', '0585', '0586', '0587', '0588', '0589', '0590', '0591', '0592', '0593', '0594', '0595', '0596', '0597', '0598', '0599', '0600', '0601', '0602', '0603', '0604', '0605', '0606', '0607', '0608', '0609', '0610', '0611', '0612', '0613', '0614', '0615', '0616', '0617', '0618', '0619', '0620', '0621', '0622', '0623', '0624', '0625', '0626', '0627', '0628', '0629', '0630', '0631', '0632', '0633', '0634', '0635', '0636', '0637', '0638', '0639', '0640', '0641', '0642', '0643', '0644', '0645', '0646', '0647', '0648', '0649', '0650', '0651', '0652', '0653', '0654', '0655', '0656', '0657', '0658', '0659', '0660', '0661', '0662', '0663', '0664', '0665', '0666', '0667', '0668', '0669', '0670', '0671', '0672', '0673', '0674', '0675', '0676', '0677', '0678', '0679', '0680', '0681', '0682', '0683', '0684', '0685', '0686', '0687', '0688', '0689', '0690', '0691', '0692', '0693', '0694', '0695', '0696', '0697', '0698', '0699', '0700', '0701', '0702', '0703', '0704', '0705', '0706', '0707', '0708', '0709', '0710', '0711', '0712', '0713', '0714', '0715', '0716', '0717', '0718', '0719', '0720', '0721', '0722', '0723', '0724', '0725', '0726', '0727', '0728', '0729', '0730', '0731', '0732', '0733', '0734', '0735', '0736', '0737', '0738', '0739', '0740', '0741', '0742', '0743', '0744', '0745', '0746', '0747', '0748', '0749', '0750', '0751', '0752', '0753', '0754', '0755', '0756', '0757', '0758', '0759', '0760', '0761', '0762', '0763', '0764', '0765', '0766', '0767', '0768', '0769', '0770', '0771', '0772', '0773', '0774', '0775', '0776', '0777', '0778', '0779', '0780', '0781', '0782', '0783', '0784', '0785', '0786', '0787', '0788', '0789', '0790', '0791', '0792', '0793', '0794', '0795', '0796', '0797', '0798', '0799', '0800', '0801', '0802', '0803', '0804', '0805', '0806', '0807', '0808', '0809', '0810', '0811', '0812', '0813', '0814', '0815', '0816', '0817', '0818', '0819', '0820', '0821', '0822', '0823', '0824', '0825', '0826', '0827', '0828', '0829', '0830', '0831', '0832', '0833', '0834', '0835', '0836', '0837', '0838', '0839', '0840', '0841', '0842', '0843', '0844', '0845', '0846', '0847', '0848', '0849', '0850', '0851', '0852', '0853', '0854', '0855', '0856', '0857', '0858', '0859', '0860', '0861', '0862', '0863', '0864', '0865', '0866', '0867', '0868', '0869', '0870', '0871', '0872', '0873', '0874', '0875', '0876', '0877', '0878', '0879', '0880', '0881', '0882', '0883', '0884', '0885', '0886', '0887', '0888', '0889', '0890', '0891', '0892', '0893', '0894', '0895', '0896', '0897', '0898', '0899', '0900', '0901', '0902', '0903', '0904', '0905', '0906', '0907', '0908', '0909', '0910', '0911', '0912', '0913', '0914', '0915', '0916', '0917', '0918', '0919', '0920', '0921', '0922', '0923', '0924', '0925', '0926', '0927', '0928', '0929', '0930', '0931', '0932', '0933', '0934', '0935', '0936', '0937', '0938', '0939', '0940', '0941', '0942', '0943', '0944', '0945', '0946', '0947', '0948', '0949', '0950', '0951', '0952', '0953', '0954', '0955', '0956', '0957', '0958', '0959', '0960', '0961', '0962', '0963', '0964', '0965', '0966', '0967', '0968', '0969', '0970', '0971', '0972', '0973', '0974', '0975', '0976', '0977', '0978', '0979', '0980', '0981', '0982', '0983', '0984', '0985', '0986', '0987', '0988', '0989', '0990', '0991', '0992', '0993', '0994', '0995', '0996', '0997', '0998', '0999', '1000'];
				choujiangList.push(cj1);

				var cj2 = new ChouJiangObj();
				cj2.totalAward = 80;
				cj2.toAwardPersons = ['3001', '3002', '3003', '3004', '3005', '3006', '3007', '3008', '3009', '3010', '3011', '3012', '3013', '3014', '3015', '3016', '3017', '3018', '3019', '3020', '3021', '3022', '3023', '3024', '3025', '3026', '3027', '3028', '3029', '3030', '3031', '3032', '3033', '3034', '3035', '3036', '3037', '3038', '3039', '3040', '3041', '3042', '3043', '3044', '3045', '3046', '3047', '3048', '3049', '3050', '3051', '3052', '3053', '3054', '3055', '3056', '3057', '3058', '3059', '3060', '3061', '3062', '3063', '3064', '3065', '3066', '3067', '3068', '3069', '3070', '3071', '3072', '3073', '3074', '3075', '3076', '3077', '3078', '3079', '3080', '3081', '3082', '3083', '3084', '3085', '3086', '3087', '3088', '3089', '3090', '3091', '3092', '3093', '3094', '3095', '3096', '3097', '3098', '3099', '3100', '3101', '3102', '3103', '3104', '3105', '3106', '3107', '3108', '3109', '3110', '3111', '3112', '3113', '3114', '3115', '3116', '3117', '3118', '3119', '3120', '3121', '3122', '3123', '3124', '3125', '3126', '3127', '3128', '3129', '3130', '3131', '3132', '3133', '3134', '3135', '3136', '3137', '3138', '3139', '3140', '3141', '3142', '3143', '3144', '3145', '3146', '3147', '3148', '3149', '3150', '3151', '3152', '3153', '3154', '3155', '3156', '3157', '3158', '3159', '3160', '3161', '3162', '3163', '3164', '3165', '3166', '3167', '3168', '3169', '3170', '3171', '3172', '3173', '3174', '3175', '3176', '3177', '3178', '3179', '3180', '3181', '3182', '3183', '3184', '3185', '3186', '3187', '3188', '3189', '3190', '3191', '3192', '3193', '3194', '3195', '3196', '3197', '3198', '3199', '3200', '3201', '3202', '3203', '3204', '3205', '3206', '3207', '3208', '3209', '3210', '3211', '3212', '3213', '3214', '3215', '3216', '3217', '3218', '3219', '3220', '3221', '3222', '3223', '3224', '3225', '3226', '3227', '3228', '3229', '3230', '3231', '3232', '3233', '3234', '3235', '3236', '3237', '3238', '3239', '3240', '3241', '3242', '3243', '3244', '3245', '3246', '3247', '3248', '3249', '3250', '3251', '3252', '3253', '3254', '3255', '3256', '3257', '3258', '3259', '3260', '3261', '3262', '3263', '3264', '3265', '3266', '3267', '3268', '3269', '3270', '3271', '3272', '3273', '3274', '3275', '3276', '3277', '3278', '3279', '3280', '3281', '3282', '3283', '3284', '3285', '3286', '3287', '3288', '3289', '3290', '3291', '3292', '3293', '3294', '3295', '3296', '3297', '3298', '3299', '3300', '3301', '3302', '3303', '3304', '3305', '3306', '3307', '3308', '3309', '3310', '3311', '3312', '3313', '3314', '3315', '3316', '3317', '3318', '3319', '3320', '3321', '3322', '3323', '3324', '3325', '3326', '3327', '3328', '3329', '3330', '3331', '3332', '3333', '3334', '3335', '3336', '3337', '3338', '3339', '3340', '3341', '3342', '3343', '3344', '3345', '3346', '3347', '3348', '3349', '3350', '3351', '3352', '3353', '3354', '3355', '3356', '3357', '3358', '3359', '3360', '3361', '3362', '3363', '3364', '3365', '3366', '3367', '3368', '3369', '3370', '3371', '3372', '3373', '3374', '3375', '3376', '3377', '3378', '3379', '3380', '3381', '3382', '3383', '3384', '3385', '3386', '3387', '3388', '3389', '3390', '3391', '3392', '3393', '3394', '3395', '3396', '3397', '3398', '3399', '3400', '3401', '3402', '3403', '3404', '3405', '3406', '3407', '3408', '3409', '3410', '3411', '3412', '3413', '3414', '3415', '3416', '3417', '3418', '3419', '3420', '3421', '3422', '3423', '3424', '3425', '3426', '3427', '3428', '3429', '3430', '3431', '3432', '3433', '3434', '3435', '3436', '3437', '3438', '3439', '3440', '3441', '3442', '3443', '3444', '3445', '3446', '3447', '3448', '3449', '3450', '3451', '3452', '3453', '3454', '3455', '3456', '3457', '3458', '3459', '3460', '3461', '3462', '3463', '3464', '3465', '3466', '3467', '3468', '3469', '3470', '3471', '3472', '3473', '3474', '3475', '3476', '3477', '3478', '3479', '3480', '3481', '3482', '3483', '3484', '3485', '3486', '3487', '3488', '3489', '3490', '3491', '3492', '3493', '3494', '3495', '3496', '3497', '3498', '3499', '3500', '3501'];
				choujiangList.push(cj2);

				var cj3 = new ChouJiangObj();
				cj3.totalAward = 60;
				cj3.toAwardPersons = ['2001', '2002', '2003', '2004', '2005', '2006', '2007', '2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016', '2017', '2018', '2019', '2020', '2021', '2022', '2023', '2024', '2025', '2026', '2027', '2028', '2029', '2030', '2031', '2032', '2033', '2034', '2035', '2036', '2037', '2038', '2039', '2040', '2041', '2042', '2043', '2044', '2045', '2046', '2047', '2048', '2049', '2050', '2051', '2052', '2053', '2054', '2055', '2056', '2057', '2058', '2059', '2060', '2061', '2062', '2063', '2064', '2065', '2066', '2067', '2068', '2069', '2070', '2071', '2072', '2073', '2074', '2075', '2076', '2077', '2078', '2079', '2080', '2081', '2082', '2083', '2084', '2085', '2086', '2087', '2088', '2089', '2090', '2091', '2092', '2093', '2094', '2095', '2096', '2097', '2098', '2099', '2100', '2101', '2102', '2103', '2104', '2105', '2106', '2107', '2108', '2109', '2110', '2111', '2112', '2113', '2114', '2115', '2116', '2117', '2118', '2119', '2120', '2121', '2122', '2123', '2124', '2125', '2126', '2127', '2128', '2129', '2130', '2131', '2132', '2133', '2134', '2135', '2136', '2137', '2138', '2139', '2140', '2141', '2142', '2143', '2144', '2145', '2146', '2147', '2148', '2149', '2150', '2151', '2152', '2153', '2154', '2155', '2156', '2157', '2158', '2159', '2160', '2161', '2162', '2163', '2164', '2165', '2166', '2167', '2168', '2169', '2170', '2171', '2172', '2173', '2174', '2175', '2176', '2177', '2178', '2179', '2180', '2181', '2182', '2183', '2184', '2185', '2186', '2187', '2188', '2189', '2190', '2191', '2192', '2193', '2194', '2195', '2196', '2197', '2198', '2199', '2200', '2201', '2202', '2203', '2204', '2205', '2206', '2207', '2208', '2209', '2210', '2211', '2212', '2213', '2214', '2215', '2216', '2217', '2218', '2219', '2220', '2221', '2222', '2223', '2224', '2225', '2226', '2227', '2228', '2229', '2230', '2231', '2232', '2233', '2234', '2235', '2236', '2237', '2238', '2239', '2240', '2241', '2242', '2243', '2244', '2245', '2246', '2247', '2248', '2249', '2250', '2251', '2252', '2253', '2254', '2255', '2256', '2257', '2258', '2259', '2260', '2261', '2262', '2263', '2264', '2265', '2266', '2267', '2268', '2269', '2270', '2271', '2272', '2273', '2274', '2275', '2276', '2277', '2278', '2279', '2280', '2281', '2282', '2283', '2284', '2285', '2286', '2287', '2288', '2289', '2290', '2291', '2292', '2293', '2294', '2295', '2296', '2297', '2298', '2299', '2300', '2301', '2302', '2303', '2304', '2305', '2306', '2307', '2308', '2309', '2310', '2311', '2312', '2313', '2314', '2315', '2316', '2317', '2318', '2319', '2320', '2321', '2322', '2323', '2324', '2325', '2326', '2327', '2328', '2329', '2330', '2331', '2332', '2333', '2334', '2335', '2336', '2337', '2338', '2339', '2340', '2341', '2342', '2343', '2344', '2345', '2346', '2347', '2348', '2349', '2350', '2351', '2352', '2353', '2354', '2355', '2356', '2357', '2358', '2359', '2360', '2361', '2362', '2363', '2364'];
				choujiangList.push(cj3);

				var cj4 = new ChouJiangObj();
				cj4.totalAward = 1;
				choujiangList.push(cj4);				
			}

			var zjry = [];
			function getRandom(Range){
				var random = Math.random();
				return Math.round(random*Range);
			}

			var intervalQuery = null;
			$(function(){
				$("tr[name='award80']").hide();
				$("#xyzx_div").hide();
				$("#stopcj").hide();
				init();
				intervalQuery = window.setInterval(intervalQueryStart, 100);
				//intervalQueryStart();
			});
			
			function intervalQueryStart(){
				$.ajax({
					 //type: "POST",
					 url: "isStart.htm",
					 success: function(msg){
					 	if(msg == "true"){
					 		start();
					 		/*window.clearTimeout(intervalQuery);
					 		intervalQuery = null;*/
					 	}
					 	console.log( "Data Saved: " + msg );
					 }										
				});
			};

			var isIntervalStart = false;
			var interval = null;
			
			function getRandomAward(count){
				zjry = new Array(count);
				
				var isAward = false;
				for(var x=0; x<count; x++){
					var v = getRandom(choujiangList[currentAwardIndex].toAwardPersons.length-1);
					isAward = isAwarded(zjry, v);
					if(isAward){
						while(true){
							v = getRandom(choujiangList[currentAwardIndex].toAwardPersons.length-1);
							isAward = isAwarded(zjry, v);
							if(!isAward){
								break;
							}
						}
					}
					zjry[x] = v;
				}
				
				/*zjry.sort(function(v1, v2){
					return v1-v2;
				})	*/			
			}
			
			function isAwarded(awardedList, award){
				var isAward = false;
				for(var a=0; a<awardedList.length; a++){
					if(award == awardedList[a]){
						isAward = true;
						break;
					}
				}
				return isAward;
			}

			function change(){
				if(currentAwardIndex < 3){
					var choujiangObj = choujiangList[currentAwardIndex];
					getRandomAward(choujiangObj.totalAward);					
					$("td").each(function(index){
						if(index < choujiangList[currentAwardIndex].totalAward){
							var v = choujiangList[currentAwardIndex].toAwardPersons[zjry[index]];
							zjry[index] = v;
							$(this).text(v);
						}
					});
				} else {
					var choujiangObj = choujiangList[currentAwardIndex];
					getRandomAward(choujiangObj.totalAward);
					var v = choujiangList[currentAwardIndex].toAwardPersons[zjry[0]];
					zjry[0] = v;
					$('#xyzx').text(v);
				}
			}

			function start(){
				$("#startcj").hide();
				$("#stopcj").show();
				if(intervalQuery != null){
					window.clearTimeout(intervalQueryStart);
				}
				if(currentAwardIndex == 1){
					$("tr[name='award80']").show();
				} else if(currentAwardIndex == 3){
					$("#xyzx_div").show();
					$("#ptj").hide();
				} else {
					$("tr[name='award80']").hide();
				}
				if(currentAwardIndex < 4){
					startInterval();
				}
			};

			function startInterval(){
				if(!isIntervalStart){
					interval = window.setInterval(change, 10);
					isIntervalStart = true;
				}
			}

			function stopInterval(){
				if(isIntervalStart){
					window.clearTimeout(interval);					
					isIntervalStart = false;
				};
			}

			function stop(){
				$("#stopcj").hide();
				$("#startcj").show();
				stopInterval();
				var choujiangObj = choujiangList[currentAwardIndex];
				choujiangObj.awardedPersons = zjry;
				var s = "";
				for(var x=0; x<zjry.length; x++){
					s += zjry[x] + ";";
				}
				s = s.substring(0, s.length-1);
				console.log("第" + currentAwardIndex + "抽奖，中奖人员为：" + s);
				submitAwardResult((currentAwardIndex+1) + ":" + s);
				if(currentAwardIndex < 3){
					currentAwardIndex++;
				}
				
				//获取幸运之星人员名单
				if(currentAwardIndex == 3 && choujiangList[currentAwardIndex].toAwardPersons.length <= 0){
					var index = 0;
					var sumAward = 0;
					for(var x=0; x<3; x++){
						var preToAwardPersons = choujiangList[x].toAwardPersons;
						var perAwardedPersons = choujiangList[x].awardedPersons;
						for(var a=0; a<preToAwardPersons.length; a++){
							var isAward = false;
							for(var b=0; b<perAwardedPersons.length; b++){
								if(preToAwardPersons[a] == perAwardedPersons[b]){
									isAward = true;
									console.log(sumAward + ": 中奖人员：" + perAwardedPersons[b]);
									sumAward++;
									break;
								}								
							}
							if(!isAward){
								choujiangList[currentAwardIndex].toAwardPersons[index] = preToAwardPersons[a];
								index ++;
							}
						}
					}
					//choujiangList[currentAwardIndex].toAwardPersons
				}
				
				intervalQuery = window.setInterval(intervalQueryStart, 100);
			}
			
			function submitAwardResult(awards){
				$.ajax({
					 type: "POST",
					 url: "awardedPerson.htm",
					 data: "awarded="+awards/*,
					 success: function(msg){
					 	if(msg == "true"){
					 		start();
					 	}
					 	console.log( "Data Saved: " + msg );
					 }	*/									
				});
			}
			
			function finish(){
				window.href = "page/award.jsp";
			}
		</script>
  </head>
  
  <body>
		<div style="position:absolute; width: 100%; height: 100%; z-index:-1">
			<!-- <img src="img/01.jpg" height="100%" width="100%"/> -->			
		</div>
		<div>
			<p id="cj_head">九江银行2014年行庆晚会抽奖环节</p>
		</div>
		
		<!--普通奖-->
		<div>
			<table width="100%"; heigth: "100%" id="ptj">
				<tr><td>0</td>	<td>1</td>	<td>2</td>	<td>3</td>	<td>4</td>	<td>5</td>	<td>6</td>	<td>7</td>	<td>8</td>	<td>9</td>	</tr>
				<tr><td>10</td>	<td>11</td>	<td>12</td>	<td>13</td>	<td>14</td>	<td>15</td>	<td>16</td>	<td>17</td>	<td>18</td>	<td>19</td>	</tr>
				<tr><td>20</td>	<td>21</td>	<td>22</td>	<td>23</td>	<td>24</td>	<td>25</td>	<td>26</td>	<td>27</td>	<td>28</td>	<td>29</td>	</tr>
				<tr><td>30</td>	<td>31</td>	<td>32</td>	<td>33</td>	<td>34</td>	<td>35</td>	<td>36</td>	<td>37</td>	<td>38</td>	<td>39</td>	</tr>
				<tr><td>40</td>	<td>41</td>	<td>42</td>	<td>43</td>	<td>44</td>	<td>45</td>	<td>46</td>	<td>47</td>	<td>48</td>	<td>49</td>	</tr>
				<tr><td>50</td>	<td>51</td>	<td>52</td>	<td>53</td>	<td>54</td>	<td>55</td>	<td>56</td>	<td>57</td>	<td>58</td>	<td>59</td>	</tr>
				<tr name="award80"><td>60</td>	<td>61</td>	<td>62</td>	<td>63</td>	<td>64</td>	<td>65</td>	<td>66</td>	<td>67</td>	<td>68</td>	<td>69</td>	</tr>
				<tr name="award80"><td>70</td>	<td>71</td>	<td>72</td>	<td>73</td>	<td>74</td>	<td>75</td>	<td>76</td>	<td>77</td>	<td>78</td>	<td>79</td>	</tr>
			</table>
		</div>
		
		<!--幸运之星-->
		<div id="xyzx_div">
			<p>幸运之星</p><form action="finish.htm"><INPUT TYPE=SUBMIT value="抽奖结束"></form>			
			<p id="xyzx"></p>
		</div>
		<!-- <div style="TEXT-ALIGN: center;"><button id="start" onclick="start();">start</button><button onclick="stop();">stop</button></div> -->
		<div style="TEXT-ALIGN: center;"><a id="startcj" onclick="start()"><img style="height: 40px; width: 40px" src="img/stop.jpg"/></a><a id="stopcj" onclick="stop();"><img style="height: 40px; width: 40px" src="img/run.jpg"/></a></div>
	</body>
</html>
