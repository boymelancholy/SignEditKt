package mcbe.boymelancholy.signeditkt.forms

import cn.nukkit.Player
import cn.nukkit.blockentity.BlockEntitySign
import itsu.mcbe.form.base.CustomForm
import itsu.mcbe.form.element.Input
import mcbe.boymelancholy.signeditkt.SignEditKt
import mcbe.boymelancholy.signeditkt.utils.FormMaker
import mcbe.boymelancholy.signeditkt.utils.Memory

class EditForm {
    fun getForm(player: Player): CustomForm? {
        val memory: Memory = SignEditKt.instance.getMemory()
        val sign: BlockEntitySign = memory.getSignMemory(player)!!
        val form: CustomForm = object : CustomForm() {
            override fun onEnter(player: Player, response: List<Any>) {
                val line1 = response[0] as String
                val line2 = response[1] as String
                val line3 = response[2] as String
                val line4 = response[3] as String
                val lines = arrayOf(
                    line1.substring(1, line1.length - 1),
                    line2.substring(1, line2.length - 1),
                    line3.substring(1, line3.length - 1),
                    line4.substring(1, line4.length - 2)
                )
                sign.setText(lines[0], lines[1], lines[2], lines[3])
                sign.saveNBT()
                player.sendMessage("> 書き換えが終わりました")
            }
        }
        val lines = sign.text
        form.id = FormMaker.formCount++
        form.title = "EDIT"
        form.addFormElement(Input().setText("1行目:").setDefaultText(lines[0]))
        form.addFormElement(Input().setText("2行目:").setDefaultText(lines[1]))
        form.addFormElement(Input().setText("3行目:").setDefaultText(lines[2]))
        form.addFormElement(Input().setText("4行目:").setDefaultText(lines[3]))
        return form
    }
}