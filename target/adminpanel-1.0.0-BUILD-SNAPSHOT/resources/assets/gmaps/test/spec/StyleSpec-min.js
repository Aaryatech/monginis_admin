describe("Adding Map Styles",function(){var a;beforeEach(function(){a=a||new GMaps({el:"#map-with-styles",lat:-12.0433,lng:-77.0283,zoom:12});a.addStyle({styledMapName:{name:"Lighter"},mapTypeId:"lighter",styles:[{elementType:"geometry",stylers:[{lightness:50}]},{elementType:"labels",stylers:[{visibility:"off"}]},]})});it("should add a MapType to the current map",function(){expect(a.map.mapTypes.get("lighter")).toBeDefined()});it("should update the styles in the current map",function(){a.setStyle("lighter");expect(a.getMapTypeId()).toEqual("lighter")})});