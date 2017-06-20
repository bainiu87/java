(function () {
    var params = {};

    //Document对象数据
    if(document) {
        params.domain = document.domain || '';
        params.url = document.URL || '';
        params.title = document.title || '';
        params.referrer = document.referrer || '';
    }
    //Window对象数据
    if(window && window.screen) {
        var sh = window.screen.height || 0;
        var sw = window.screen.width || 0;
        params.res = sw + "*" + sh;
        params.cd = window.screen.colorDepth || 0;
    }
    //navigator对象数据
    if(navigator) {
        params.lang = navigator.language || '';
        params.platform = navigator.platform || '';

        var ua = navigator.userAgent;
        if (ua.length > 250) {
            ua = ua.substring(0, 250);
        }

        if (ua.indexOf("Maxthon") != -1) {
            params.brower = "Maxthon";
        } else if (ua.indexOf("MSIE") != -1) {
            params.brower = "MSIE";
        } else if (ua.indexOf("Firefox") != -1) {
            params.brower = "Firefox";
        } else if (ua.indexOf("Chrome") != -1) {
            params.brower = "Chrome";
        } else if (ua.indexOf("Opera") != -1) {
            params.brower = "Opera";
        } else if (ua.indexOf("Safari") != -1) {
            params.brower = "Safari";
        } else {
            params.brower = "Other";
        }
    }

    params.cookieid = getStatId("zstatId");

    var phone = document.getElementById("sessionPhone").val();
    if(phone){
        params.phone = phone;
    }else{
        params.phone = "phone";
    }

    if(remote_ip_info.country){
        params.country = remote_ip_info.country;
        params.province = remote_ip_info.province;
        params.city = remote_ip_info.city;
        params.isp = remote_ip_info.isp;
    }


    //解析_maq配置
    if(_maq) {
        for(var i in _maq) {
            switch(_maq[i][0]) {
                case 'web_id':
                    params.webid = _maq[i][1];
                    break;
                default:
                    break;
            }
        }
    }
    //拼接参数串
    var args = '';
    for(var i in params) {
        if(args != '') {
            args += '&';
        }
        args += i + '=' + encodeURIComponent(params[i]);
    }

    //通过Image对象请求后端脚本
    var img = new Image(1, 1);
    img.src = 'http://tj.vjuzhen.com/stat.gif?' + args;
})();




/**
 * 获取StatId
 */
function getStatId(statIdName) {
    var statId = getCookie(statIdName);
    if (statId != null && statId.length > 0) {
        return statId;
    } else {
        setStatId(statIdName);
        return getStatId(statIdName);
    }
}
/**
 * 获取cookieId
 */
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) {
                c_end = document.cookie.length;
            }
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";
}
/**
 * 设置StatId
 */
function setStatId(statIdName) {
    var cookieId = genStatId();
    setCookie(statIdName, cookieId, 365);
}
/**
 * 生成statId
 */
function genStatId() {
    var cookieId = getTimestamp();
    cookieId = "zstat" + "-" + cookieId + "-" + Math.round(Math.random() * 3000000000);
    return cookieId;
}
/**
 * 设置cookieId
 */
function setCookie(c_name, value, expiredays) {
    var exdate=new Date()
    exdate.setDate(exdate.getDate()+expiredays)
    document.cookie=c_name+ "=" +escape(value)+ ((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
}
/**
 * 获取当前时间戳
 */
function getTimestamp() {
    var timestamp = Date.parse(new Date());
    return timestamp;
}