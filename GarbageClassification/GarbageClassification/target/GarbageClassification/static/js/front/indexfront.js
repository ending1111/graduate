
;(function () {
    function indexFunc() {
        var _utils = layui.util, $ = layui.jquery, _layer = layui.layer,
            _dateFormat = layui.dateFormat, _pager = layui.laypage, _form = layui.form, _md5 = layui.md5,
            _sha1 = layui.sha1, _multipleCheckbox = layui.multipleCheckbox;
        // fetch data
        var productListContainer = $('#productListContainer');
        var _productList = {};
        function getProductList() {
            productListContainer.html('');
            $.get('/api/app/loadProductList', {}, function (result) {
                if (result.code == 0) {
                    var productList = result.content;
                    console.log(productList);
                    $.each(productList, function (idx, product) {
                        var div = $('<div class="layui-col-xs6 layui-col-sm6 layui-col-md3" style="">\n' +
                            '              <a href="/admin/getProductById?productId='+product.id+'" >\n' +
                            '                <div class="img-txt">\n' +
                            '                  <img style="width: 100%;height:160px;" src="'+product.image+'" alt="">\n' +
                            '                  <h3>'+product.name+'</h3>\n' +
                            '            <div class="investInfo" >\n' +
                            '              <ul>\n' +
                            '                <li style="width:25%"><em style="font-style: normal;">'+product.updownrange+'</em>%<br>日化收益</li>\n' +
                            '                <li style="width:35%"><em style="font-style: normal;">'+product.no+'</em><br>投资期限</li>\n' +
                            '                <li style="width:40%">￥<em style="font-style: normal;">'+product.price+'</em>元<br>起投金额</li>\n' +
                            '                <div class="clear"></div>\n' +
                            '              </ul>\n' +
                            '            </div>'+
                            '                </div>\n' +
                            '              </a>\n' +
                            '            </div>');
                        productListContainer.append(div);
                        _productList[product.id] = product;
                    });
                } else {
                    _layer.msg(result.msg ? result.msg : '获取用户列表失败');
                }
            });
        }


        // get first page
        getProductList();
    }

    layui.use(
        [
            'laypage',
            'util',
            'jquery',
            'layer',
            'dateFormat',
            'form',
            'md5',
            'sha1',
            'multipleCheckbox'
        ],
        indexFunc
    );
} ());