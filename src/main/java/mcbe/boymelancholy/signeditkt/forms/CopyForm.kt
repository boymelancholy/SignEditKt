package mcbe.boymelancholy.signeditkt.forms

import cn.nukkit.Player
import cn.nukkit.blockentity.BlockEntitySign
import itsu.mcbe.form.base.CustomForm
import itsu.mcbe.form.element.Input
import mcbe.boymelancholy.signeditkt.SignEditKt
import mcbe.boymelancholy.signeditkt.utils.FormMaker
import mcbe.boymelancholy.signeditkt.utils.Memory

class CopyForm {
    fun getForm(player: Player): CustomForm? {
        val memory: Memory = SignEditKt.instance.getMemory()
        val sign: BlockEntitySign = memory.getSignMemory(player)!!
        val form: CustomForm = object : CustomForm() {
            override fun onEnter(player: Player, response: List<Any>) {
                val aliasRaw = response[0] as String
                val alias = aliasRaw.substring(1, aliasRaw.length - 2)
                val lines = sign.text
                if (memory.hasCopiedMemory(player, alias)!!) {
                    val maker: FormMaker = memory.getMakerMemory(player)!!
                    maker.sendForm(FormMaker.error)
                } else {
                    memory.setCopiedMemory(player, alias, lines)
                    player.sendMessage("> コピーが終わりました")
                }
            }
        }
        form.id = FormMaker.formCount++
        form.title = "COPY"
        form.addFormElement(Input().setText("保存するエイリアス名を入力してください: "))
        return form
    }
}