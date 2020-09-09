package mcbe.boymelancholy.signeditkt.forms

import cn.nukkit.Player
import itsu.mcbe.form.base.ModalForm
import mcbe.boymelancholy.signeditkt.SignEditKt
import mcbe.boymelancholy.signeditkt.utils.FormMaker

class CopyExceptionForm {
    fun getForm(player: Player): ModalForm? {
        val maker: FormMaker = SignEditKt.instance.getMemory().getMakerMemory(player)!!
        val form: ModalForm = object : ModalForm() {
            override fun onButton1Click(player: Player) {
                maker.sendForm(FormMaker.copy)
            }

            override fun onButton2Click(player: Player) {
                player.sendMessage("> 処理を中断しました")
            }
        }
        form.id = FormMaker.formCount++
        form.title = "ERROR"
        form.content = "すでに存在するエイリアス名は使えません"
        form.setButton1Text("再入力する").button2Text = "中断する"
        return form
    }
}