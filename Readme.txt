◆Spring MVCのサーブレットフィルタについてのメモ
※最低限の動きを確認したいため、入力チェック等はなし

◇インデックス
■type01：GenericFilterBeanの利用
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
