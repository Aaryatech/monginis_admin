describe("Adding layers",function(){var a,b,c=[];beforeEach(function(){a=a||new GMaps({el:"#map-with-layers",lat:-12.0433,lng:-77.0283,zoom:12})});describe("Single layer",function(){beforeEach(function(){b=b||a.addLayer("traffic")});it("should be added in the current map",function(){expect(b.getMap()).toEqual(a.map)});it("should be removed from the current map",function(){a.removeLayer("traffic");expect(b.getMap()).toBeNull()})});describe("Multiple layers",function(){beforeEach(function(){if(c.length==0){c.push(a.addLayer("transit"));c.push(a.addLayer("bicycling"))}});it("should be added in the current map",function(){expect(c[0].getMap()).toEqual(a.map);expect(c[1].getMap()).toEqual(a.map)});it("should be removed from the current map",function(){a.removeLayer("transit");a.removeLayer("bicycling");expect(c[0].getMap()).toBeNull();expect(c[1].getMap()).toBeNull()})})});