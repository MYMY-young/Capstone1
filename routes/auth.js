const express = require('express');
const passport = require('passport');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
const User = require('../models/user');
const { isLoggedIn, isNotLoggedIn, verifyToken } = require('./middlewares');

const router = express.Router();

/* 회원가입 라우터 */
router.post('/join', /*isNotLoggedIn ,*/async(req,res,next)=>{
    const { email, nick, password } = req.body;
    try{
        const exUser = await User.findOne({ where: { email:email }});
        if(exUser){ //이미 존재하는 이메일인 경우
            return res.json({
                success: "false",
                errMessage: "이미 존재하는 이메일 입니다.",
            });
        }
        const hash = await bcrypt.hash(password, 12);
        await User.create({ email: email, nick: nick, password: hash, });
        return res.json({
            success: true,
            errMessage: null,
        });
    } catch{
        console.error(error);
        return next(error);
    }
})

/* 로그인 라우터 (session)
router.post('/login', isNotLoggedIn, (req,res,next)=>{
    passport.authenticate('local', (authError, user, info) => { //done이 두번째 인수를 실행한다.
        if(authError) {
            console.error(authError);
            return next(authError);
        }
        if(!user) {
            return res.json({
                success: "false",
                errMessage: info.message,
            });
        }
        return req.login(user, (loginError) => { //passport.serializeUser 호출
            if(loginError){
                console.error(loginError);
                return next(loginError);
            }
            req.session.save(()=>{
                res.json({
                    success: "true",
                    errMessage: null,
                });
            })
        });
    })(req,res,next);
})*/

/* 로그인 라우터 */
router.post('/login', (req,res,next)=>{
    //session 을 사용하지 않는다. serialize, deserialize 호출하지 않는다.
    passport.authenticate('local', {session:false}, (authError, user, info) => { //done이 두번째 인수를 실행한다.
        if(authError) {
            console.error(authError);
            return next(authError);
        }
        if(!user) {
            return res.json({
                success: "false",
                errMessage: info.message,
            });
        }
        return req.login(user, (loginError) => { 
            if(loginError){
                console.error(loginError);
                return next(loginError);
            }
            const token = jwt.sign({
                id: user.id,
                nick: user.nick,
            },process.env.JWT_SECRET,{
                expiresIn: '30m',
            });
            res.json({
                success: "true",
                errMessage: null,
                token,
            });
        });
    })(req,res,next);
})
 
router.get('/test', verifyToken, (req,res,next)=>{
    res.json({
        success: "true",
        errMessage: null,
    });
})

/* 로그아웃 라우터 
router.get('/logout', isLoggedIn, (req,res,next)=>{
    req.logout();
    req.session.destroy();
    res.json({
        success: "true",
        errMessage: null,
    });
})*/


module.exports = router;