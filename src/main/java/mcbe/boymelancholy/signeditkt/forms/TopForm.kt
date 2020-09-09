package mcbe.boymelancholy.signeditkt.forms

import cn.nukkit.Player
import itsu.mcbe.form.base.SimpleForm
import itsu.mcbe.form.element.Button
import mcbe.boymelancholy.signeditkt.SignEditKt
import mcbe.boymelancholy.signeditkt.utils.FormMaker


class TopForm {
    fun getForm(): SimpleForm? {
        val form: SimpleForm = object : SimpleForm() {
            override fun onEnter(player: Player, index: Int) {
                val maker: FormMaker = SignEditKt.instance.getMemory().getMakerMemory(player)!!
                if (index == -1) {
                    return
                }
                when (index) {
                    0 -> maker.sendForm(FormMaker.edit)
                    1 -> {
                        SignEditKt.instance.getMemory().initCopiedMemory(player)
                        maker.sendForm(FormMaker.copy)
                    }
                    2 -> if (SignEditKt.instance.getMemory().hasCopiedMemory(player)!!) {
                        maker.sendForm(FormMaker.paste)
                    } else {
                        player.sendMessage("> 貼り付けられる文字がありません")
                    }
                    3 -> maker.sendForm(FormMaker.clear)
                    4 -> if (SignEditKt.instance.getMemory().hasCopiedMemory(player)!!) {
                        maker.sendForm(FormMaker.delete)
                    } else {
                        player.sendMessage("> 消せる文字がありません")
                    }
                }
            }
        }
        form.id = FormMaker.formCount++
        form.title = "TOP"
        form.content = "実行する処理を選択してください"
        val edit: Button = Button().setText("書き換え").setImage("url", "https://i.imgur.com/QmA6UZR.png")
        val copy: Button = Button().setText("コピー").setImage("url", "https://i.imgur.com/vGXIZhS.png")
        val paste: Button = Button().setText("ペースト").setImage("url", "https://i.imgur.com/hA4v71w.png")
        val clear: Button = Button().setText("全削除").setImage("url", "https://i.imgur.com/4hBz3Ij.png")
        val delete: Button = Button().setText("キャッシュ削除").setImage("url", "https://i.imgur.com/n8W4leS.png")
        form.addButton(edit)
        form.addButton(copy)
        form.addButton(paste)
        form.addButton(clear)
        form.addButton(delete)
        return form
    }
}