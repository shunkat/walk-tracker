# アプリ概要
このアプリの目的は、位置情報および健康データの収集と蓄積です。
使用者は実験協力者のみを想定しています。

## ページ構成
- （初回登録）被験者データ登録ページ
- （初回登録）アプリへの権限許可ページ
- マイページ

## 機能
- ログイン機能
- （バックグラウンド）位置情報収集機能
- （バックグラウンド）健康情報（歩数、ケイデンス、歩行速度、運動頻度）収集機能
- 自分の運動データの確認機能（バックエンド側からオンオフの切り替えを可能にする想定）

## DBへ蓄積されるデータ
- 位置情報から加工された、移動距離、移動時間、移動速度、移動経路、外出頻度、外出時間帯
- 健康情報から加工された、歩数、ケイデンス、歩行速度、運動頻度

## その他
- プライバシー保護のために全てのデータは、通信、蓄積時に暗号化されます。
- 蓄積されたデータは本人と、研究者である加藤のみが閲覧可能です。
- 収集されたデータは、研究目的以外では使用しません。
- このアプリは、研究目的のために作成されたものであり、一般の方には公開しません。そのためストア等での公開もされない予定です。
- ただし、研究の透明性確保と今後の技術の発展のためにソースコードはgithubにて公開します。


