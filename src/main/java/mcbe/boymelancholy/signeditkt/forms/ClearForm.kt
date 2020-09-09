package mcbe.boymelancholy.signeditkt.forms

import cn.nukkit.Player
import cn.nukkit.blockentity.BlockEntitySign
import itsu.mcbe.form.base.ModalForm
import mcbe.boymelancholy.signeditkt.SignEditKt
import mcbe.boymelancholy.signeditkt.utils.FormMaker
import mcbe.boymelancholy.signeditkt.utils.Memory

class ClearForm {
    fun getForm(player: Player): ModalForm? {
        val memory: Memory = SignEditKt.instance.getMemory()
        val sign: BlockEntitySign = memory.getSignMemory(player)!!
        val form: ModalForm = object : ModalForm() {
            override fun onButton1Click(player: Player) {
                sign.setText("", "", "", "")
                sign.saveNBT()
                player.sendMessage("> 全削除しました")
            }

            override fun onButton2Click(player: Player) {
                player.sendMessage("> 処理を中断しました")
            }
        }
        form.id = FormMaker.formCount++
        form.title = "CLEAR"
        form.content = "この看板の文字を全削除しますか"
        form.button1Text = "はい"
        form.button2Text = "いいえ"
        return form
    }
}