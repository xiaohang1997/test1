//序列化表单（将表单序列化为json对象）,它依赖jquery
/**
 * {
 *     ’key':'value',
 *     'key':'value'
 * }
 * @param form
 * @returns {{}}
 */
var serializeObject=function(form){
    var o = {};
    $.each(form.serializeArray(), function(index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
            o[this['name']] = this['value'];
        }
    });
    return o;
};