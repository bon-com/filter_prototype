◆Spring MVCのサーブレットフィルタについてのメモ
※最低限の動きを確認したいため、入力チェック等はなし

◇インデックス
■type01：GenericFilterBeanの利用（単純なフィルター）
・説明：
GenericFilterBeanを継承して作成したフィルタークラスでは、リクエストのたびにdoFilter()が実行される。
さらに、内部でフォワードやリダイレクトが発生した場合にも、新たなリクエスト処理とみなされるため、doFilter()が再度呼び出される可能性がある。

例えば、ブラウザから/cart/confirmにリクエストし、Controller内でforward:/WEB-INF/views/confirm.jspへフォワードした場合、
1回目のController呼び出しと、フォワード先のJSPレンダリング処理で2回doFilter()が実行される。（※同一リクエスト内でのフィルター処理が二重実行される）

・用途：
純粋なリクエストログなど、都度記録したい処理

・使用方法（SpringのDIコンテナにアクセスしない場合）
①GenericFilterBeanを継承してフィルタークラスを作成
②web.xml（サーブレットコンテナ）にURLパターンを指定して登録

■type02：GenericFilterBeanの利用（DIコンテナのBeanを参照するフィルター）
・説明
GenericFilterBeanを継承したフィルタークラスを利用して、ＤＩコンテナで管理するＢｅａｎを参照する。

・使用方法
①GenericFilterBeanを継承してフィルタークラスを作成
②ルートアプリケーションコンテキストのDIコンテナに登録（WEBアプリケーションコンテキスト側だとエラーになった）
③web.xmlに「org.springframework.web.filter.DelegatingFilterProxy」を指定し、
DelegatingFilterProxy経由でサーブレットフィルタを実行する。
DelegatingFilterProxyはSpringのDIコンテナに登録されているサーブレットフィルタに処理を委譲するフィルタ。
④RequestContextFilterを使用する
スレッドローカルにする設定を実施してやらないと、内部でスレッドローカルにする前にこのフィルターが呼ばれてエラーになった。
ざっくりと、リクエスト情報をスレッドに紐づけているらしい。
この紐づけ（スレッドローカル）により、リクエストのたびにHttpServletRequestがThreadLocalに自動的に紐付き、セッションスコープなどの取得が可能になる。
→Springがリクエストやセッションスコープを扱えるのは、裏で「今どのスレッドがどのリクエストを処理しているか」をThreadLocal経由で管理しているから。
　その仕組みの橋渡しがRequestContextFilter（またはRequestContextListener）とのこと。

■type03：OncePerRequestFilterの利用
・説明
OncePerRequestFilterを継承したフィルタークラスを利用して、ログ出力させる。
同一リクエスト内で１回だけ処理が実行される。
GenericFilterBeanを継承している。

・機能説明
このフィルターを使用して、リクエスト～レスポンス完了までの処理時間（ミリ秒）を測っている
[Client sends HTTP request]
       ↓
[Filter: start timing] ← ★ここで System.currentTimeMillis()
       ↓
[Spring MVC: DispatcherServlet → Controller → Service → View]
       ↓
[Filter: end timing]   ← ★ここで System.currentTimeMillis()
       ↓
[Response sent to client]


◇補足
基本的にセッションスコープBeanをログ出力させない
また、セッションスコープBeanはWebアプリケーションコンテキスト側で管理させたい
いったんプロジェクトだけ残す