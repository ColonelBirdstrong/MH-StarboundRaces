function init()
   effect.addStatModifierGroup({{stat = "specialStatusImmunity", amount = 1}, {stat = "stunImmunity", amount = 1}, {stat = "slimeImmunity", amount = 1}, {stat = "tarStatusImmunity", amount = 1}})

   script.setUpdateDelta(0)
end

function update(dt)

end

function uninit()
  
end
