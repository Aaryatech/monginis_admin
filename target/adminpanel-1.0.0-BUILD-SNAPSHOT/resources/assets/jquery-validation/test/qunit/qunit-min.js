(function(i){var C,e,F,h,s=0,G=(f(0)||"").replace(/(:\d+)+\)?/,"").replace(/.+\//,""),z=Object.prototype.toString,p=Object.prototype.hasOwnProperty,d=i.Date,c={setTimeout:typeof i.setTimeout!=="undefined",sessionStorage:(function(){var I="qunit-test-string";try{sessionStorage.setItem(I,I);sessionStorage.removeItem(I);return true}catch(J){return false}}())},H=function(J){var I,L,K=J.toString();if(K.substring(0,7)==="[object"){I=J.name?J.name.toString():"Error";L=J.message?J.message.toString():"";if(I&&L){return I+": "+L}else{if(I){return I}else{if(L){return L}else{return"Error"}}}}else{return K}},j=function(K){var I,L,J=C.is("array",K)?[]:{};for(I in K){if(p.call(K,I)){L=K[I];J[I]=L===Object(L)?j(L):L}}return J};function b(I){E(this,I);this.assertions=[];this.testNumber=++b.count}b.count=0;b.prototype={init:function(){var K,J,I,L=u("qunit-tests");if(L){J=document.createElement("strong");J.innerHTML=this.nameHtml;K=document.createElement("a");K.innerHTML="Rerun";K.href=C.url({testNumber:this.testNumber});I=document.createElement("li");I.appendChild(J);I.appendChild(K);I.className="running";I.id=this.id="qunit-test-output"+s++;L.appendChild(I)}},setup:function(){if(this.module!==F.previousModule){if(F.previousModule){A("moduleDone",C,{name:F.previousModule,failed:F.moduleStats.bad,passed:F.moduleStats.all-F.moduleStats.bad,total:F.moduleStats.all})}F.previousModule=this.module;F.moduleStats={all:0,bad:0};A("moduleStart",C,{name:this.module})}else{if(F.autorun){A("moduleStart",C,{name:this.module})}}F.current=this;this.testEnvironment=E({setup:function(){},teardown:function(){}},this.moduleTestEnvironment);this.started=+new d();A("testStart",C,{name:this.testName,module:this.module});C.current_testEnvironment=this.testEnvironment;if(!F.pollution){B()}if(F.notrycatch){this.testEnvironment.setup.call(this.testEnvironment);return}try{this.testEnvironment.setup.call(this.testEnvironment)}catch(I){C.pushFailure("Setup failed on "+this.testName+": "+(I.message||I),t(I,1))}},run:function(){F.current=this;var I=u("qunit-testresult");if(I){I.innerHTML="Running: <br/>"+this.nameHtml}if(this.async){C.stop()}this.callbackStarted=+new d();if(F.notrycatch){this.callback.call(this.testEnvironment,C.assert);this.callbackRuntime=+new d()-this.callbackStarted;return}try{this.callback.call(this.testEnvironment,C.assert);this.callbackRuntime=+new d()-this.callbackStarted}catch(J){this.callbackRuntime=+new d()-this.callbackStarted;C.pushFailure("Died on test #"+(this.assertions.length+1)+" "+this.stack+": "+(J.message||J),t(J,0));B();if(F.blocking){C.start()}}},teardown:function(){F.current=this;if(F.notrycatch){if(typeof this.callbackRuntime==="undefined"){this.callbackRuntime=+new d()-this.callbackStarted}this.testEnvironment.teardown.call(this.testEnvironment);return}else{try{this.testEnvironment.teardown.call(this.testEnvironment)}catch(I){C.pushFailure("Teardown failed on "+this.testName+": "+(I.message||I),t(I,1))}}o()},finish:function(){F.current=this;if(F.requireExpects&&this.expected===null){C.pushFailure("Expected number of assertions to be defined, but expect() was not called.",this.stack)}else{if(this.expected!==null&&this.expected!==this.assertions.length){C.pushFailure("Expected "+this.expected+" assertions, but "+this.assertions.length+" were run",this.stack)}else{if(this.expected===null&&!this.assertions.length){C.pushFailure("Expected at least one assertion, but none were run - call expect(0) to accept zero assertions.",this.stack)}}}var M,S,Q,P,K,R,O,N=this,L=0,J=0,I=u("qunit-tests");this.runtime=+new d()-this.started;F.stats.all+=this.assertions.length;F.moduleStats.all+=this.assertions.length;if(I){O=document.createElement("ol");O.className="qunit-assert-list";for(M=0;M<this.assertions.length;M++){S=this.assertions[M];R=document.createElement("li");R.className=S.result?"pass":"fail";R.innerHTML=S.message||(S.result?"okay":"failed");O.appendChild(R);if(S.result){L++}else{J++;F.stats.bad++;F.moduleStats.bad++}}if(C.config.reorder&&c.sessionStorage){if(J){sessionStorage.setItem("qunit-test-"+this.module+"-"+this.testName,J)}else{sessionStorage.removeItem("qunit-test-"+this.module+"-"+this.testName)}}if(J===0){l(O,"qunit-collapsed")}P=document.createElement("strong");P.innerHTML=this.nameHtml+" <b class='counts'>(<b class='failed'>"+J+"</b>, <b class='passed'>"+L+"</b>, "+this.assertions.length+")</b>";y(P,"click",function(){var T=P.parentNode.lastChild,U=a(T,"qunit-collapsed");(U?n:l)(T,"qunit-collapsed")});y(P,"dblclick",function(U){var T=U&&U.target?U.target:i.event.srcElement;if(T.nodeName.toLowerCase()==="span"||T.nodeName.toLowerCase()==="b"){T=T.parentNode}if(i.location&&T.nodeName.toLowerCase()==="strong"){i.location=C.url({testNumber:N.testNumber})}});K=document.createElement("span");K.className="runtime";K.innerHTML=this.runtime+" ms";R=u(this.id);R.className=J?"fail":"pass";R.removeChild(R.firstChild);Q=R.firstChild;R.appendChild(P);R.appendChild(Q);R.appendChild(K);R.appendChild(O)}else{for(M=0;M<this.assertions.length;M++){if(!this.assertions[M].result){J++;F.stats.bad++;F.moduleStats.bad++}}}A("testDone",C,{name:this.testName,module:this.module,failed:J,passed:this.assertions.length-J,total:this.assertions.length,duration:this.runtime});C.reset();F.current=undefined},queue:function(){var J,K=this;k(function(){K.init()});function I(){k(function(){K.setup()});k(function(){K.run()});k(function(){K.teardown()});k(function(){K.finish()})}J=C.config.reorder&&c.sessionStorage&&+sessionStorage.getItem("qunit-test-"+this.module+"-"+this.testName);if(J){I()}else{k(I,true)}}};C={module:function(I,J){F.currentModule=I;F.currentModuleTestEnvironment=J;F.modules[I]=true},asyncTest:function(I,J,K){if(arguments.length===2){K=J;J=null}C.test(I,J,K,true)},test:function(I,L,N,J){var M,K="<span class='test-name'>"+v(I)+"</span>";if(arguments.length===2){N=L;L=null}if(F.currentModule){K="<span class='module-name'>"+v(F.currentModule)+"</span>: "+K}M=new b({nameHtml:K,testName:I,expected:L,async:J,callback:N,module:F.currentModule,moduleTestEnvironment:F.currentModuleTestEnvironment,stack:f(2)});if(!g(M)){return}M.queue()},expect:function(I){if(arguments.length===1){F.current.expected=I}else{return F.current.expected}},start:function(I){if(F.semaphore===undefined){C.begin(function(){setTimeout(function(){C.start(I)})});return}F.semaphore-=I||1;if(F.semaphore>0){return}if(F.semaphore<0){F.semaphore=0;C.pushFailure("Called start() while already started (QUnit.config.semaphore was 0 already)",null,f(2));return}if(c.setTimeout){i.setTimeout(function(){if(F.semaphore>0){return}if(F.timeout){clearTimeout(F.timeout)}F.blocking=false;q(true)},13)}else{F.blocking=false;q(true)}},stop:function(I){F.semaphore+=I||1;F.blocking=true;if(F.testTimeout&&c.setTimeout){clearTimeout(F.timeout);F.timeout=i.setTimeout(function(){C.ok(false,"Test timed out");F.semaphore=1;C.start()},F.testTimeout)}}};e={ok:function(I,L){if(!F.current){throw new Error("ok() assertion outside test context, was "+f(2))}I=!!I;var K,J={module:F.current.module,name:F.current.testName,result:I,message:L};L=v(L||(I?"okay":"failed"));L="<span class='test-message'>"+L+"</span>";if(!I){K=f(2);if(K){J.source=K;L+="<table><tr class='test-source'><th>Source: </th><td><pre>"+v(K)+"</pre></td></tr></table>"}}A("log",C,J);F.current.assertions.push({result:I,message:L})},equal:function(K,J,I){C.push(J==K,K,J,I)},notEqual:function(K,J,I){C.push(J!=K,K,J,I)},propEqual:function(K,J,I){K=j(K);J=j(J);C.push(C.equiv(K,J),K,J,I)},notPropEqual:function(K,J,I){K=j(K);J=j(J);C.push(!C.equiv(K,J),K,J,I)},deepEqual:function(K,J,I){C.push(C.equiv(K,J),K,J,I)},notDeepEqual:function(K,J,I){C.push(!C.equiv(K,J),K,J,I)},strictEqual:function(K,J,I){C.push(J===K,K,J,I)},notStrictEqual:function(K,J,I){C.push(J!==K,K,J,I)},"throws":function(M,K,J){var O,N=K,I=false;if(typeof K==="string"){J=K;K=null}F.current.ignoreGlobalErrors=true;try{M.call(F.current.testEnvironment)}catch(L){O=L}F.current.ignoreGlobalErrors=false;if(O){if(!K){I=true;N=null}else{if(C.objectType(K)==="regexp"){I=K.test(H(O))}else{if(O instanceof K){I=true}else{if(K.call({},O)===true){N=null;I=true}}}}C.push(I,O,N,J)}else{C.pushFailure(J,null,"No exception was thrown.")}}};E(C,e);C.raises=e["throws"];C.equals=function(){C.push(false,false,false,"QUnit.equals has been deprecated since 2009 (e88049a0), use QUnit.equal instead")};C.same=function(){C.push(false,false,false,"QUnit.same has been deprecated since 2009 (e88049a0), use QUnit.deepEqual instead")};(function(){function I(){}I.prototype=C;C=new I();C.constructor=I}());F={queue:[],blocking:true,hidepassed:false,reorder:true,altertitle:true,requireExpects:false,urlConfig:[{id:"noglobals",label:"Check for Globals",tooltip:"Enabling this will test if any test introduces new properties on the `window` object. Stored as query-strings."},{id:"notrycatch",label:"No try-catch",tooltip:"Enabling this will run tests outside of a try-catch block. Makes debugging exceptions in IE reasonable. Stored as query-strings."}],modules:{},begin:[],done:[],log:[],testStart:[],testDone:[],moduleStart:[],moduleDone:[]};if(typeof exports==="undefined"){E(i,C);i.QUnit=C}(function(){var K,I=i.location||{search:"",protocol:"file:"},N=I.search.slice(1).split("&"),L=N.length,J={},M;if(N[0]){for(K=0;K<L;K++){M=N[K].split("=");M[0]=decodeURIComponent(M[0]);M[1]=M[1]?decodeURIComponent(M[1]):true;J[M[0]]=M[1]}}C.urlParams=J;F.filter=J.filter;F.module=J.module;F.testNumber=parseInt(J.testNumber,10)||null;C.isLocal=I.protocol==="file:"}());E(C,{assert:e,config:F,init:function(){E(F,{stats:{all:0,bad:0},moduleStats:{all:0,bad:0},started:+new d(),updateRate:1000,blocking:false,autostart:true,autorun:false,filter:"",queue:[],semaphore:1});var L,J,I,K=u("qunit");if(K){K.innerHTML="<h1 id='qunit-header'>"+v(document.title)+"</h1><h2 id='qunit-banner'></h2><div id='qunit-testrunner-toolbar'></div><h2 id='qunit-userAgent'></h2><ol id='qunit-tests'></ol>"}L=u("qunit-tests");J=u("qunit-banner");I=u("qunit-testresult");if(L){L.innerHTML=""}if(J){J.className=""}if(I){I.parentNode.removeChild(I)}if(L){I=document.createElement("p");I.id="qunit-testresult";I.className="result";L.parentNode.insertBefore(I,L);I.innerHTML="Running...<br/>&nbsp;"}},reset:function(){var I=u("qunit-fixture");if(I){I.innerHTML=F.fixture}},triggerEvent:function(K,I,J){if(document.createEvent){J=document.createEvent("MouseEvents");J.initMouseEvent(I,true,true,K.ownerDocument.defaultView,0,0,0,0,0,false,false,false,false,0,null);K.dispatchEvent(J)}else{if(K.fireEvent){K.fireEvent("on"+I)}}},is:function(I,J){return C.objectType(J)===I},objectType:function(K){if(typeof K==="undefined"){return"undefined"}if(K===null){return"null"}var I=z.call(K).match(/^\[object\s(.*)\]$/),J=I&&I[1]||"";switch(J){case"Number":if(isNaN(K)){return"nan"}return"number";case"String":case"Boolean":case"Array":case"Date":case"RegExp":case"Function":return J.toLowerCase()}if(typeof K==="object"){return"object"}return undefined},push:function(I,O,M,L){if(!F.current){throw new Error("assertion outside test context, was "+f())}var J,N,K={module:F.current.module,name:F.current.testName,result:I,message:L,actual:O,expected:M};L=v(L)||(I?"okay":"failed");L="<span class='test-message'>"+L+"</span>";J=L;if(!I){M=v(C.jsDump.parse(M));O=v(C.jsDump.parse(O));J+="<table><tr class='test-expected'><th>Expected: </th><td><pre>"+M+"</pre></td></tr>";if(O!==M){J+="<tr class='test-actual'><th>Result: </th><td><pre>"+O+"</pre></td></tr>";J+="<tr class='test-diff'><th>Diff: </th><td><pre>"+C.diff(M,O)+"</pre></td></tr>"}N=f();if(N){K.source=N;J+="<tr class='test-source'><th>Source: </th><td><pre>"+v(N)+"</pre></td></tr>"}J+="</table>"}A("log",C,K);F.current.assertions.push({result:!!I,message:J})},pushFailure:function(K,L,M){if(!F.current){throw new Error("pushFailure() assertion outside test context, was "+f(2))}var I,J={module:F.current.module,name:F.current.testName,result:false,message:K};K=v(K)||"error";K="<span class='test-message'>"+K+"</span>";I=K;I+="<table>";if(M){I+="<tr class='test-actual'><th>Result: </th><td><pre>"+v(M)+"</pre></td></tr>"}if(L){J.source=L;I+="<tr class='test-source'><th>Source: </th><td><pre>"+v(L)+"</pre></td></tr>"}I+="</table>";A("log",C,J);F.current.assertions.push({result:false,message:I})},url:function(K){K=E(E({},C.urlParams),K);var J,I="?";for(J in K){if(!p.call(K,J)){continue}I+=encodeURIComponent(J)+"="+encodeURIComponent(K[J])+"&"}return i.location.protocol+"//"+i.location.host+i.location.pathname+I.slice(0,-1)},extend:E,id:u,addEvent:y});E(C.constructor.prototype,{begin:w("begin"),done:w("done"),log:w("log"),testStart:w("testStart"),testDone:w("testDone"),moduleStart:w("moduleStart"),moduleDone:w("moduleDone")});if(typeof document==="undefined"||document.readyState==="complete"){F.autorun=true}C.load=function(){A("begin",C,{});var U,I,N,T,P,M,Q,S,Y,K,W,J,V,L=0,R="",O="",X=E({},F);C.init();E(F,X);F.blocking=false;P=F.urlConfig.length;for(N=0;N<P;N++){K=F.urlConfig[N];if(typeof K==="string"){K={id:K,label:K,tooltip:"[no tooltip available]"}}F[K.id]=C.urlParams[K.id];O+="<input id='qunit-urlconfig-"+v(K.id)+"' name='"+v(K.id)+"' type='checkbox'"+(F[K.id]?" checked='checked'":"")+" title='"+v(K.tooltip)+"'><label for='qunit-urlconfig-"+v(K.id)+"' title='"+v(K.tooltip)+"'>"+K.label+"</label>"}R+="<label for='qunit-modulefilter'>Module: </label><select id='qunit-modulefilter' name='modulefilter'><option value='' "+(F.module===undefined?"selected='selected'":"")+">< All Modules ></option>";for(N in F.modules){if(F.modules.hasOwnProperty(N)){L+=1;R+="<option value='"+v(encodeURIComponent(N))+"' "+(F.module===N?"selected='selected'":"")+">"+v(N)+"</option>"}}R+="</select>";Y=u("qunit-userAgent");if(Y){Y.innerHTML=navigator.userAgent}U=u("qunit-header");if(U){U.innerHTML="<a href='"+C.url({filter:undefined,module:undefined,testNumber:undefined})+"'>"+U.innerHTML+"</a> "}S=u("qunit-testrunner-toolbar");if(S){I=document.createElement("input");I.type="checkbox";I.id="qunit-filter-pass";y(I,"click",function(){var aa,Z=document.getElementById("qunit-tests");if(I.checked){Z.className=Z.className+" hidepass"}else{aa=" "+Z.className.replace(/[\n\t\r]/g," ")+" ";Z.className=aa.replace(/ hidepass /," ")}if(c.sessionStorage){if(I.checked){sessionStorage.setItem("qunit-filter-passed-tests","true")}else{sessionStorage.removeItem("qunit-filter-passed-tests")}}});if(F.hidepassed||c.sessionStorage&&sessionStorage.getItem("qunit-filter-passed-tests")){I.checked=true;Q=document.getElementById("qunit-tests");Q.className=Q.className+" hidepass"}S.appendChild(I);T=document.createElement("label");T.setAttribute("for","qunit-filter-pass");T.setAttribute("title","Only show tests and assertons that fail. Stored in sessionStorage.");T.innerHTML="Hide passed tests";S.appendChild(T);W=document.createElement("span");W.innerHTML=O;J=W.getElementsByTagName("input");x(J,"click",function(Z){var ab={},aa=Z.target||Z.srcElement;ab[aa.name]=aa.checked?true:undefined;i.location=C.url(ab)});S.appendChild(W);if(L>1){V=document.createElement("span");V.setAttribute("id","qunit-modulefilter-container");V.innerHTML=R;y(V.lastChild,"change",function(){var aa=V.getElementsByTagName("select")[0],Z=decodeURIComponent(aa.options[aa.selectedIndex].value);i.location=C.url({module:(Z==="")?undefined:Z})});S.appendChild(V)}}M=u("qunit-fixture");if(M){F.fixture=M.innerHTML}if(F.autostart){C.start()}};y(i,"load",C.load);h=i.onerror;i.onerror=function(K,L,I){var J=false;if(h){J=h(K,L,I)}if(J!==true){if(C.config.current){if(C.config.current.ignoreGlobalErrors){return true}C.pushFailure(K,L+":"+I)}else{C.test("global failure",E(function(){C.pushFailure(K,L+":"+I)},{validTest:g}))}return false}return J};function r(){F.autorun=true;if(F.currentModule){A("moduleDone",C,{name:F.currentModule,failed:F.moduleStats.bad,passed:F.moduleStats.all-F.moduleStats.bad,total:F.moduleStats.all})}var L,K,I=u("qunit-banner"),M=u("qunit-tests"),N=+new d()-F.started,O=F.stats.all-F.stats.bad,J=["Tests completed in ",N," milliseconds.<br/>","<span class='passed'>",O,"</span> assertions of <span class='total'>",F.stats.all,"</span> passed, <span class='failed'>",F.stats.bad,"</span> failed."].join("");if(I){I.className=(F.stats.bad?"qunit-fail":"qunit-pass")}if(M){u("qunit-testresult").innerHTML=J}if(F.altertitle&&typeof document!=="undefined"&&document.title){document.title=[(F.stats.bad?"\u2716":"\u2714"),document.title.replace(/^[\u2714\u2716] /i,"")].join(" ")}if(F.reorder&&c.sessionStorage&&F.stats.bad===0){for(L=0;L<sessionStorage.length;L++){K=sessionStorage.key(L++);if(K.indexOf("qunit-test-")===0){sessionStorage.removeItem(K)}}}if(i.scrollTo){i.scrollTo(0,0)}A("done",C,{failed:F.stats.bad,passed:O,total:F.stats.all,runtime:N})}function g(M){var I,K=F.filter&&F.filter.toLowerCase(),J=F.module&&F.module.toLowerCase(),L=(M.module+": "+M.testName).toLowerCase();if(M.callback&&M.callback.validTest===g){delete M.callback.validTest;return true}if(F.testNumber){return M.testNumber===F.testNumber}if(J&&(!M.module||M.module.toLowerCase()!==J)){return false}if(!K){return true}I=K.charAt(0)!=="!";if(!I){K=K.slice(1)}if(L.indexOf(K)!==-1){return I}return !I}function t(L,M){M=M===undefined?3:M;var J,I,K;if(L.stacktrace){return L.stacktrace.split("\n")[M+3]}else{if(L.stack){J=L.stack.split("\n");if(/^error$/i.test(J[0])){J.shift()}if(G){I=[];for(K=M;K<J.length;K++){if(J[K].indexOf(G)!==-1){break}I.push(J[K])}if(I.length){return I.join("\n")}}return J[M]}else{if(L.sourceURL){if(/qunit.js$/.test(L.sourceURL)){return}return L.sourceURL+":"+L.line}}}}function f(J){try{throw new Error()}catch(I){return t(I,J)}}function v(I){if(!I){return""}I=I+"";return I.replace(/['"<>&]/g,function(J){switch(J){case"'":return"&#039;";case'"':return"&quot;";case"<":return"&lt;";case">":return"&gt;";case"&":return"&amp;"}})}function k(J,I){F.queue.push(J);if(F.autorun&&!F.blocking){q(I)}}function q(J){function I(){q(J)}var K=new d().getTime();F.depth=F.depth?F.depth+1:1;while(F.queue.length&&!F.blocking){if(!c.setTimeout||F.updateRate<=0||((new d().getTime()-K)<F.updateRate)){F.queue.shift()()}else{i.setTimeout(I,13);break}}F.depth--;if(J&&!F.blocking&&!F.queue.length&&F.depth===0){r()}}function B(){F.pollution=[];if(F.noglobals){for(var I in i){if(!p.call(i,I)||/^qunit-test-output/.test(I)){continue}F.pollution.push(I)}}}function o(){var J,K,I=F.pollution;B();J=m(F.pollution,I);if(J.length>0){C.pushFailure("Introduced global variable(s): "+J.join(", "))}K=m(I,F.pollution);if(K.length>0){C.pushFailure("Deleted global variable(s): "+K.join(", "))}}function m(K,J){var M,L,I=K.slice();for(M=0;M<I.length;M++){for(L=0;L<J.length;L++){if(I[M]===J[L]){I.splice(M,1);M--;break}}}return I}function E(J,I){for(var K in I){if(I[K]===undefined){delete J[K]}else{if(K!=="constructor"||J!==i){J[K]=I[K]}}}return J}function y(K,J,I){if(K.addEventListener){K.addEventListener(J,I,false)}else{K.attachEvent("on"+J,I)}}function x(I,L,K){var J=I.length;while(J--){y(I[J],L,K)}}function a(J,I){return(" "+J.className+" ").indexOf(" "+I+" ")>-1}function l(J,I){if(!a(J,I)){J.className+=(J.className?" ":"")+I}}function n(J,I){var K=" "+J.className+" ";while(K.indexOf(" "+I+" ")>-1){K=K.replace(" "+I+" "," ")}J.className=i.jQuery?jQuery.trim(K):(K.trim?K.trim():K)}function u(I){return !!(typeof document!=="undefined"&&document&&document.getElementById)&&document.getElementById(I)}function w(I){return function(J){F[I].push(J)}}function A(K,L,I){var J,M;if(C.hasOwnProperty(K)){C[K].call(L,I)}else{M=F[K];for(J=0;J<M.length;J++){M[J].call(L,I)}}}C.equiv=(function(){function M(Q,P,O){var R=C.objectType(Q);if(R){if(C.objectType(P[R])==="function"){return P[R].apply(P,O)}else{return P[R]}}}var L,N=[],J=[],I=Object.getPrototypeOf||function(O){return O.__proto__},K=(function(){function O(P,Q){if(P instanceof Q.constructor||Q instanceof P.constructor){return Q==P}else{return Q===P}}return{string:O,"boolean":O,number:O,"null":O,"undefined":O,nan:function(P){return isNaN(P)},date:function(P,Q){return C.objectType(P)==="date"&&Q.valueOf()===P.valueOf()},regexp:function(P,Q){return C.objectType(P)==="regexp"&&Q.source===P.source&&Q.global===P.global&&Q.ignoreCase===P.ignoreCase&&Q.multiline===P.multiline&&Q.sticky===P.sticky},"function":function(){var P=N[N.length-1];return P!==Object&&typeof P!=="undefined"},array:function(Q,S){var U,T,P,R;if(C.objectType(Q)!=="array"){return false}P=S.length;if(P!==Q.length){return false}J.push(S);for(U=0;U<P;U++){R=false;for(T=0;T<J.length;T++){if(J[T]===S[U]){R=true}}if(!R&&!L(S[U],Q[U])){J.pop();return false}}J.pop();return true},object:function(Q,S){var V,T,R,P=true,U=[],W=[];if(S.constructor!==Q.constructor){if(!((I(S)===null&&I(Q)===Object.prototype)||(I(Q)===null&&I(S)===Object.prototype))){return false}}N.push(S.constructor);J.push(S);for(V in S){R=false;for(T=0;T<J.length;T++){if(J[T]===S[V]){R=true}}U.push(V);if(!R&&!L(S[V],Q[V])){P=false;break}}N.pop();J.pop();for(V in Q){W.push(V)}return P&&L(U.sort(),W.sort())}}}());L=function(){var O=[].slice.apply(arguments);if(O.length<2){return true}return(function(Q,P){if(Q===P){return true}else{if(Q===null||P===null||typeof Q==="undefined"||typeof P==="undefined"||C.objectType(Q)!==C.objectType(P)){return false}else{return M(Q,K,[P,Q])}}}(O[0],O[1])&&arguments.callee.apply(this,O.splice(1,O.length-1)))};return L}());C.jsDump=(function(){function I(O){return'"'+O.toString().replace(/"/g,'\\"')+'"'}function J(O){return O+""}function L(T,O,Q){var R=K.separator(),S=K.indent(),P=K.indent(1);if(O.join){O=O.join(","+R+P)}if(!O){return T+Q}return[T,P+O,S+Q].join(R)}function N(P,O){var R=P.length,Q=new Array(R);this.up();while(R--){Q[R]=this.parse(P[R],undefined,O)}this.down();return L("[",Q,"]")}var M=/^function (\w+)/,K={parse:function(S,Q,O){O=O||[];var R,P,T=this.parsers[Q||this.typeOf(S)];Q=typeof T;R=D(S,O);if(R!==-1){return"recursion("+(R-O.length)+")"}if(Q==="function"){O.push(S);P=T.call(this,S,O);O.pop();return P}return(Q==="string")?T:this.parsers.error},typeOf:function(P){var O;if(P===null){O="null"}else{if(typeof P==="undefined"){O="undefined"}else{if(C.is("regexp",P)){O="regexp"}else{if(C.is("date",P)){O="date"}else{if(C.is("function",P)){O="function"}else{if(typeof P.setInterval!==undefined&&typeof P.document!=="undefined"&&typeof P.nodeType==="undefined"){O="window"}else{if(P.nodeType===9){O="document"}else{if(P.nodeType){O="node"}else{if(z.call(P)==="[object Array]"||(typeof P.length==="number"&&typeof P.item!=="undefined"&&(P.length?P.item(0)===P[0]:(P.item(0)===null&&typeof P[0]==="undefined")))){O="array"}else{if(P.constructor===Error.prototype.constructor){O="error"}else{O=typeof P}}}}}}}}}}return O},separator:function(){return this.multiline?this.HTML?"<br />":"\n":this.HTML?"&nbsp;":" "},indent:function(O){if(!this.multiline){return""}var P=this.indentChar;if(this.HTML){P=P.replace(/\t/g,"   ").replace(/ /g,"&nbsp;")}return new Array(this._depth_+(O||0)).join(P)},up:function(O){this._depth_+=O||1},down:function(O){this._depth_-=O||1},setParser:function(O,P){this.parsers[O]=P},quote:I,literal:J,join:L,_depth_:1,parsers:{window:"[Window]",document:"[Document]",error:function(O){return'Error("'+O.message+'")'},unknown:"[Unknown]","null":"null","undefined":"undefined","function":function(Q){var P="function",O="name" in Q?Q.name:(M.exec(Q)||[])[1];if(O){P+=" "+O}P+="( ";P=[P,C.jsDump.parse(Q,"functionArgs"),"){"].join("");return L(P,C.jsDump.parse(Q,"functionCode"),"}")},array:N,nodelist:N,"arguments":N,object:function(T,O){var P=[],S,R,U,Q;C.jsDump.up();S=[];for(R in T){S.push(R)}S.sort();for(Q=0;Q<S.length;Q++){R=S[Q];U=T[R];P.push(C.jsDump.parse(R,"key")+": "+C.jsDump.parse(U,undefined,O))}C.jsDump.down();return L("{",P,"}")},node:function(P){var S,Q,O,R=C.jsDump.HTML?"&lt;":"<",V=C.jsDump.HTML?"&gt;":">",W=P.nodeName.toLowerCase(),T=R+W,U=P.attributes;if(U){for(Q=0,S=U.length;Q<S;Q++){O=U[Q].nodeValue;if(O&&O!=="inherit"){T+=" "+U[Q].nodeName+"="+C.jsDump.parse(O,"attribute")}}}T+=V;if(P.nodeType===3||P.nodeType===4){T+=P.nodeValue}return T+R+"/"+W+V},functionArgs:function(Q){var P,O=Q.length;if(!O){return""}P=new Array(O);while(O--){P[O]=String.fromCharCode(97+O)}return" "+P.join(", ")+" "},key:I,functionCode:"[code]",attribute:I,string:I,date:I,regexp:J,number:J,"boolean":J},HTML:false,indentChar:"  ",multiline:true};return K}());function D(K,L){if(L.indexOf){return L.indexOf(K)}for(var I=0,J=L.length;I<J;I++){if(L[I]===K){return I}}return -1}C.diff=(function(){function I(M,N){var J,K={},L={};for(J=0;J<N.length;J++){if(!p.call(K,N[J])){K[N[J]]={rows:[],o:null}}K[N[J]].rows.push(J)}for(J=0;J<M.length;J++){if(!p.call(L,M[J])){L[M[J]]={rows:[],n:null}}L[M[J]].rows.push(J)}for(J in K){if(!p.call(K,J)){continue}if(K[J].rows.length===1&&p.call(L,J)&&L[J].rows.length===1){N[K[J].rows[0]]={text:N[K[J].rows[0]],row:L[J].rows[0]};M[L[J].rows[0]]={text:M[L[J].rows[0]],row:K[J].rows[0]}}}for(J=0;J<N.length-1;J++){if(N[J].text!=null&&N[J+1].text==null&&N[J].row+1<M.length&&M[N[J].row+1].text==null&&N[J+1]==M[N[J].row+1]){N[J+1]={text:N[J+1],row:N[J].row+1};M[N[J].row+1]={text:M[N[J].row+1],row:J+1}}}for(J=N.length-1;J>0;J--){if(N[J].text!=null&&N[J-1].text==null&&N[J].row>0&&M[N[J].row-1].text==null&&N[J-1]==M[N[J].row-1]){N[J-1]={text:N[J-1],row:N[J].row-1};M[N[J].row-1]={text:M[N[J].row-1],row:J-1}}}return{o:M,n:N}}return function(P,Q){P=P.replace(/\s+$/,"");Q=Q.replace(/\s+$/,"");var M,N,O="",K=I(P===""?[]:P.split(/\s+/),Q===""?[]:Q.split(/\s+/)),J=P.match(/\s+/g),L=Q.match(/\s+/g);if(J==null){J=[" "]}else{J.push(" ")}if(L==null){L=[" "]}else{L.push(" ")}if(K.n.length===0){for(M=0;M<K.o.length;M++){O+="<del>"+K.o[M]+J[M]+"</del>"}}else{if(K.n[0].text==null){for(Q=0;Q<K.o.length&&K.o[Q].text==null;Q++){O+="<del>"+K.o[Q]+J[Q]+"</del>"}}for(M=0;M<K.n.length;M++){if(K.n[M].text==null){O+="<ins>"+K.n[M]+L[M]+"</ins>"}else{N="";for(Q=K.n[M].row+1;Q<K.o.length&&K.o[Q].text==null;Q++){N+="<del>"+K.o[Q]+J[Q]+"</del>"}O+=" "+K.n[M].text+L[M]+N}}}return O}}());if(typeof exports!=="undefined"){E(exports,C)}}((function(){return this}.call())));