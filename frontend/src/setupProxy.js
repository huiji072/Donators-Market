const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app){

    //비회원 - 8081
    app.use(
        createProxyMiddleware('/main', {
            target: process.env.REACT_APP_ANONYMOUS,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/itemDtl', {
            target: process.env.REACT_APP_ANONYMOUS,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/members/new', {
            target: process.env.REACT_APP_ANONYMOUS,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/members/login/token', {
            target: process.env.REACT_APP_ANONYMOUS,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/members/loginInfo', {
            target: process.env.REACT_APP_ANONYMOUS,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/cart/new', {
            target: process.env.REACT_APP_ANONYMOUS,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/carts', {
            target: process.env.REACT_APP_ANONYMOUS,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/cartItem/**', {
            target: process.env.REACT_APP_ANONYMOUS,
            changeOrigin: true
        })
    )



    //기부자 - 8082

    app.use(
        createProxyMiddleware('/admin/item', {
            target: process.env.REACT_APP_DONATOR,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/item/itemMng', {
            target: process.env.REACT_APP_DONATOR,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/item/search', {
            target: process.env.REACT_APP_DONATOR,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/offers', {
            target: process.env.REACT_APP_DONATOR,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/offer/*/cancel', {
            target: process.env.REACT_APP_DONATOR,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/offer/new', {
            target: process.env.REACT_APP_DONATOR,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/item/registerAnswer', {
            target: process.env.REACT_APP_DONATOR,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/cart/offers', {
            target: process.env.REACT_APP_DONATOR,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/queue/send/invoice', {
            target: process.env.REACT_APP_DONATOR,
            changeOrigin: true
        })
    )



    // 피기부기관 - 8083
    app.use(
        createProxyMiddleware('/cart/new', {
            target: process.env.REACT_APP_DONATEE,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/cartItem/**', {
            target: process.env.REACT_APP_DONATEE,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/item/registerQuestion', {
            target: process.env.REACT_APP_DONATEE,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/order/new', {
            target: process.env.REACT_APP_DONATEE,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/orders', {
            target: process.env.REACT_APP_DONATEE,
            changeOrigin: true
        })
    )
    
    app.use(
        createProxyMiddleware('/orders/*', {
            target: process.env.REACT_APP_DONATEE,
            changeOrigin: true
        })
    )


    app.use(
        createProxyMiddleware('/order/*/cancel', {
            target: process.env.REACT_APP_DONATEE,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/cart/new', {
            target: process.env.REACT_APP_DONATEE,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/cart/orders', {
            target: process.env.REACT_APP_DONATEE,
            changeOrigin: true
        })
    )

    app.use(
        createProxyMiddleware('/queue/invoice/order', {
            target: process.env.REACT_APP_DONATEE,
            changeOrigin: true
        })
    )


    app.use(
        createProxyMiddleware('/logistics/search', {
            target: process.env.REACT_APP_DONATEE,
            changeOrigin: true
        })
    )
    

    //관리자 - 8084
    app.use(
        createProxyMiddleware('/members/managements', {
            target: process.env.REACT_APP_MANAGER,
            changeOrigin: true
        })
    )

  




  };
