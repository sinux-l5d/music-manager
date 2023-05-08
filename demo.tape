Output demo.mp4

Require java

Set Shell "bash"
Set FontSize 20
Set Width 1200
Set Height 600
Set Margin 3

Type "java -jar ./target/musicmanager-1.0-SNAPSHOT.jar" Enter Sleep 500ms

Type "test.mm" Enter Sleep 2s

Type "lib list" Sleep 500ms Enter Sleep 2s
Type "lib search artist Stro" Sleep 500ms Enter Sleep 2s
Type "select 1" Sleep 500ms Enter Sleep 1s

Type "pl list" Sleep 500ms Enter Sleep 2s
Type "pl create <3" Sleep 500ms Enter Sleep 2s
Type "pl list" Sleep 500ms Enter Sleep 2s
Type "pl add 1" Sleep 500ms Enter Sleep 2s
Type "pl list" Sleep 500ms Enter Sleep 2s

Type "select 2" Sleep 500ms Enter Sleep 1s

Type "pl add 1" Sleep 500ms Enter Sleep 2s
Type "pl show 1" Sleep 500ms Enter Sleep 2s

Type "select 0" Sleep 500ms Enter Sleep 1s

Sleep 4s

Type "quit" Sleep 500ms Enter

Sleep 1s
