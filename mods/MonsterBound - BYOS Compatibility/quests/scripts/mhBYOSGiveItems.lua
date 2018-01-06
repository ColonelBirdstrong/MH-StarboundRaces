require("/quests/scripts/portraits.lua")
require("/quests/scripts/questutil.lua")

function init()
	setPortraits()
end

function questStart()
	if player.introComplete() then
		quest.complete()
		return
	end
end

function questComplete()
	questutil.questCompleteActions()
end

function update(dt)
	if player.introComplete() then
		quest.complete()
		return
	end
end
